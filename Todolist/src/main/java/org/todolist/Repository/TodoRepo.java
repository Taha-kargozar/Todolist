package org.todolist.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.todolist.Model.Todolists;


public interface TodoRepo extends JpaRepository<Todolists,Long> {

}

