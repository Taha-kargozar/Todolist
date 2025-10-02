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
    public ResponseEntity<Long> save(@RequestBody TodolistDTO todolistDTO) {
        Todolists todolists = new Todolists();
        todolists.setDescription(todolistDTO.getDescription());
        todolists.setNameTodo(todolistDTO.getNameTodo());
        todolists.setCompleted(Boolean.FALSE);
        todolists.setDateTodo(todolistDTO.getDateTodo());
        todoService.CreateTodo(todolists);
        return ResponseEntity.ok().body(Long.valueOf(200));
    }


    @GetMapping("/allTodo")
    public ResponseEntity<Page<Todolists>> getall(Pageable pageable) {
        Page<Todolists> todos = todoService.getAllTodo(pageable);
        return ResponseEntity.ok(todos);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<Todolists> getbyid(@PathVariable Long id) {
        try {
            todoService.GetById(id);
            return ResponseEntity.ok().body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(null);
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

    @PutMapping("update/{id}")
    public ResponseEntity<Todolists> update(@RequestBody @PathVariable TodolistDTO todolistDTO) {
        Todolists extodo = todoService.GetById(todolistDTO.getIdTodo());
        extodo.setDateTodo(todolistDTO.getDateTodo());
        extodo.setDescription(todolistDTO.getDescription());
        extodo.setNameTodo(todolistDTO.getNameTodo());
        extodo.setCompleted(todolistDTO.isCompleted());
        Todolists updatetodo = todoService.UpdateTodo(extodo);
        return ResponseEntity.ok().body(updatetodo);
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
