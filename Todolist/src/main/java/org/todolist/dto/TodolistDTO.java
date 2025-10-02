package org.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.todolist.Model.Todolists;

import java.time.LocalDate;

public class TodolistDTO {
    private String nameTodo;
    private Long idTodo;
    private boolean completed = false;
    private String description;
    private LocalDate dateTodo;

    @NotBlank(message = "title.todolist.blank")
    public String getNameTodo() {
        return nameTodo;
    }

    public void setNameTodo(String nameTodo) {
        this.nameTodo = nameTodo;
    }
    @NotNull(message = "no.id")
    public Long getIdTodo() {
        return idTodo;
    }

    public void setIdTodo(Long idTodo) {
        this.idTodo = idTodo;
    }
    @NotNull(message = "choose.is.completed")
    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    @NotEmpty(message = "Desc.todolist.blank")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @NotNull(message = "dateTodo.todolist.null")
    public LocalDate getDateTodo() {
        return dateTodo;
    }

    public void setDateTodo(LocalDate dateTodo) {
        this.dateTodo = dateTodo;
    }


}
