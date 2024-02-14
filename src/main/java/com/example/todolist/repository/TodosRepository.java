package com.example.todolist.repository;

import com.example.todolist.domain.Todos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodosRepository extends JpaRepository<Todos, Long> {

}
