package org.todolist.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.todolist.Model.Todolists;

public interface TodoService {

    Todolists CreateTodo(Todolists todolists);

    Page<Todolists> getAllTodo(Pageable pageable);

    Todolists UpdateTodo(Todolists todolists);

    Todolists GetById(Long Id);

    Todolists DeleteTodo(Long Id);

    void ChangeCompleted(Todolists todolists,Boolean complete);
}
