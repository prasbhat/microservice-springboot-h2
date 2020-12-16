package com.myzonesoft.microservice.todo.repository;

import com.myzonesoft.microservice.todo.model.Todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
