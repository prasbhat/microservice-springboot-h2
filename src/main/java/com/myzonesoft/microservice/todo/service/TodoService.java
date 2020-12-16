package com.myzonesoft.microservice.todo.service;

import com.myzonesoft.microservice.todo.model.Todo;

import java.util.List;

/**
 * TodoService interface is the Autowired into the Controller.
 * This interface can be implemented by many Service implementation classes.
 */
public interface TodoService {

    /**
     * Method declaration for listing all the items of the To-do tasks
     * @return List of all items of the To-do tasks
     */
    List<Todo> findAll();

    /**
     * Method declaration for listing an item of the To-do task based on id
     * @param id Unique identifier of the to-do task
     * @return To-do task based on id
     */
    Todo findById(long id);

    /**
     * Method declaration for deleting an item from the To-do tasks based on id
     * @param id Unique identifier of the to-do task to be deleted
     * @return True or False
     */
    boolean deleteById(long id);

    /**
     * Method declaration for updating an item of the To-do task
     * @param todoItem To-do task object to be updated
     * @return Updated to-do task object
     */
    Todo createOrUpdate(Todo todoItem);

    /**
     * Method declaration for listing all the items of the Status variable
     * @return List of all values of the Status variable
     */
    List<String> getTodoStatusAsList();
}
