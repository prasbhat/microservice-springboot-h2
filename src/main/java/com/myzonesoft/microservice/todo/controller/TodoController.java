package com.myzonesoft.microservice.todo.controller;

import com.myzonesoft.microservice.todo.model.Todo;
import com.myzonesoft.microservice.todo.service.TodoService;
import com.myzonesoft.microservice.todo.util.TodoApplicationConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

import java.util.List;

/**
 * TodoController is the Controller class for the To-do Tracker Application.
 * This class is responsible for exposing the REST APIs.
 *
 * CrossOrigin: This is used to accept the requests from cross domain URLs
 *         'http://localhost:3000' = React Frontend Server Domain URL
 *         'http://localhost:4200' = Angular Frontend Server Domain URL
 */
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class TodoController implements TodoApplicationConstants {

    //Variable declarations
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);
    private final String className = this.getClass().getSimpleName();

    /**
     * The Autowired Service Interface
     */
    @Autowired
    private TodoService todoService;

    /**
     * Method for listing all the items of the To-do tasks
     * This method accepts HTTP_REQUEST_METHOD:GET
     *
     * @return List of all items of the To-do tasks
     */
    @GetMapping("/findAll")
    public List<Todo> findAllTodoList() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        List<Todo> todoList = todoService.findAll();
        LOGGER.debug("TodoList=="+todoList.toString());
        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return todoList;
    }

    /**
     * Method for listing an item of the To-do task based on id
     * This method accepts HTTP_REQUEST_METHOD:GET
     *
     * @param id Unique identifier of the to-do task
     * @return To-do task based on id
     */
    @GetMapping("/find/{id}")
    public Todo findById(@PathVariable long id) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        Todo todoItem = todoService.findById(id);
        LOGGER.debug("TodoItem=="+todoItem.toString());
        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return todoItem;
    }

    /**
     * Method for deleting an item from the To-do tasks based on id
     * This method accepts HTTP_REQUEST_METHOD:DELETE
     *
     * @param id Unique identifier of the to-do task to be deleted
     * @return True or False
     */
    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable long id) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        Boolean isDeleted = todoService.deleteById(id);
        LOGGER.debug("Todo Item deleted=="+isDeleted);
        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return isDeleted;
    }

    /**
     * Method for updating an item of the To-do task
     * This method accepts HTTP_REQUEST_METHOD:PUT
     *
     * @param todoItem To-do task object to be updated
     * @return Updated to-do task object
     */
    @PutMapping("/update")
    public Todo updateTodoItem(@RequestBody Todo todoItem) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        todoItem = todoService.createOrUpdate(todoItem);
        LOGGER.debug("TodoItem=="+todoItem.toString());
        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return todoItem;
    }

    /**
     * Method for creating an item in the To-do task
     * This method accepts HTTP_REQUEST_METHOD:POST
     *
     * @param todoItem To-do task object to be created
     * @return Newly created To-do task object
     */
    @PostMapping("/create")
    public Todo createTodoItem(@RequestBody Todo todoItem) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        todoItem = todoService.createOrUpdate(todoItem);
        LOGGER.debug("TodoItem=="+todoItem.toString());
        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return todoItem;
    }

    /**
     * Method for listing all the items of the Status variable
     * This method accepts HTTP_REQUEST_METHOD:GET
     *
     * @return List of all values of thr Status variable
     */
    @GetMapping("/getStatus")
    public List<String> getTodoStatus() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        List<String> todoStatusList = todoService.getTodoStatusAsList();
        LOGGER.debug("todoStatusList=="+todoStatusList);
        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return todoStatusList;
    }
}
