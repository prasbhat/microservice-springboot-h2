package com.myzonesoft.microservice.todo.repository;

import com.myzonesoft.microservice.todo.model.TodoTaskComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoTaskCommentsRepository extends JpaRepository<TodoTaskComments, Long> {

}
