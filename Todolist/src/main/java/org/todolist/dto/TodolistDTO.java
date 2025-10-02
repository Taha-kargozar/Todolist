package org.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.todolist.Model.Todolists;

import java.time.LocalDate;

public record TodolistDTO( String nameTodo,
        Long idTodo,
        boolean completed,
        String description,
        LocalDate dateTodo) {

    @NotBlank(message = "title.todolist.blank")
    public String getNameTodo() {
        return nameTodo;
    }

    public Long getIdTodo() {
        return idTodo;
    }


    public boolean isCompleted() {
        return completed;
    }



    @NotEmpty(message = "Desc.todolist.blank")
    public String getDescription() {
        return description;
    }

    @NotNull(message = "dateTodo.todolist.null")
    public LocalDate getDateTodo() {
        return dateTodo;
    }

}
