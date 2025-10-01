package org.todolist.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Todolists {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTodo;

    private boolean completed = false;
    private String description;
    private String nameTodo;
    private LocalDate dateTodo;

    public Todolists() {}

    public Todolists(Long idTodo, boolean completed, String description, String nameTodo, LocalDate dateTodo) {
        this.idTodo = idTodo;
        this.completed = completed;
        this.description = description;
        this.nameTodo = nameTodo;
        this.dateTodo = dateTodo;
    }

    public Long getIdTodo() {
        return idTodo;
    }

    public void setIdTodo(Long idTodo) {
        this.idTodo = idTodo;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameTodo() {
        return nameTodo;
    }

    public void setNameTodo(String nameTodo) {
        this.nameTodo = nameTodo;
    }

    public LocalDate getDateTodo() {
        return dateTodo;
    }

    public void setDateTodo(LocalDate dateTodo) {
        this.dateTodo = dateTodo;
    }
}
