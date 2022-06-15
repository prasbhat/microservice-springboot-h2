package com.myzonesoft.todo.microservice.repository;

import com.myzonesoft.todo.microservice.model.TodoTaskComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoTaskCommentsRepository extends JpaRepository<TodoTaskComments, Long> {

}
