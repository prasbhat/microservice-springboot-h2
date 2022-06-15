package com.myzonesoft.todo.microservice.repository;

import com.myzonesoft.todo.microservice.model.Tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {

}
