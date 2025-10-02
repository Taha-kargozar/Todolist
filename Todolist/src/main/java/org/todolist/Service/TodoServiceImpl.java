package org.todolist.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.todolist.Execption.NotFoundException;
import org.todolist.Execption.NullException;
import org.todolist.Model.Todolists;
import org.todolist.Repository.TodoRepo;

import java.time.LocalDate;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepo todoRepo;

    @Autowired
    public TodoServiceImpl(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    @Override
    public Todolists CreateTodo(Todolists todolists) {
        if (todolists.getNameTodo() == null || todolists.getNameTodo().trim().isEmpty()) {
            throw new NullException("title is empty");
        } else if (todolists.getDescription() == null || todolists.getDescription().trim().isEmpty()) {
            throw new NullException("desc is empty");
        } else if (todolists.getDateTodo() == null) {
            throw new NullException("date is null");
        } else if (todolists.getDateTodo().isBefore(LocalDate.now())) {
            throw new RuntimeException("date should not in the past");
        }
        return todoRepo.save(todolists);
    }

    @Override
    public Page<Todolists> getAllTodo(Pageable pageable) {
        return todoRepo.findAll(pageable);
    }

    @Override
    public Todolists UpdateTodo(Todolists todolists) {
        return todoRepo.save(todolists);
    }

    @Override
    public Todolists GetById(Long id) {
        return todoRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("todolist.not.found"));
    }

    @Override
    public Todolists DeleteTodo(Long Id) {
        if (Id == null || Id <= 0) {
            throw new NotFoundException("id is not true");
        }
        if (todoRepo.existsById(Id)) {
            todoRepo.deleteById(Id);
        } else {
            throw  new NotFoundException("todo not found");
        }
        return null;
    }

}
