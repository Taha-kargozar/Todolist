package org.todolist.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.todolist.Model.Todolists;
import java.time.LocalDate;

public interface TodoRepo extends JpaRepository<Todolists,Long> {

     Page<Todolists> findByDescriptionContaining(String keyword, org.springframework.data.domain.Pageable pageable);

     Page<Todolists> findByCompleted(Boolean comp, org.springframework.data.domain.Pageable pageable);

     Page<Todolists> findByDateTodo(LocalDate date, org.springframework.data.domain.Pageable pageable);

     Page<Todolists> findByNameTodo(String name, org.springframework.data.domain.Pageable pageable);
}

