package org.todolist.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.todolist.Model.Todolists;
import org.todolist.Service.TodoService;
import org.todolist.dto.TodolistDTO;

import java.util.Map;

@RestController
@RequestMapping("/todolists")
@CrossOrigin
public class TodoController {
    @Autowired
    private final TodoService todoService;


    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @PostMapping("/addTodo")
    public ResponseEntity<Long> save(@RequestBody @Valid TodolistDTO todolistDTO) {
        Todolists todolists = new Todolists();
        todolists.setDescription(todolistDTO.getDescription());
        todolists.setNameTodo(todolistDTO.getNameTodo());
        todolists.setCompleted(Boolean.FALSE);
        todolists.setDateTodo(todolistDTO.getDateTodo());
        todoService.CreateTodo(todolists);
        return ResponseEntity.ok().body(null);
    }


    @GetMapping("/allTodo")
    public ResponseEntity<Page<Todolists>> getall(Pageable pageable) {
        Page<Todolists> todos = todoService.getAllTodo(pageable);
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Todolists> getById(@PathVariable Long id) {
        try {
            Todolists todo = todoService.GetById(id);
            return ResponseEntity.ok(todo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<Todolists> delete(@PathVariable Long id) {
        try {
            todoService.DeleteTodo(id);
            return ResponseEntity.ok().body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Todolists> update(@PathVariable Long id, @RequestBody TodolistDTO dto) {

        try {
            if (!id.equals(dto.getIdTodo())) {
                return ResponseEntity.badRequest().build();
            }

            Todolists existing = todoService.GetById(id);

            existing.setNameTodo(dto.getNameTodo());
            existing.setDescription(dto.getDescription());
            existing.setDateTodo(dto.getDateTodo());
            existing.setCompleted(dto.isCompleted());

            Todolists updated = todoService.UpdateTodo(existing);
            return ResponseEntity.ok(updated);

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PatchMapping("/toggle/{id}")
    public ResponseEntity<?> toggleCompleted(@PathVariable Long id, @RequestBody Map<String, Boolean> body) {
        Boolean completed = body.get("completed");
        if (completed == null) {
            return ResponseEntity.badRequest().build();
        }

        Todolists todo = todoService.GetById(id);
        todo.setCompleted(completed);

        return ResponseEntity.ok(todoService.UpdateTodo(todo));
    }

}
