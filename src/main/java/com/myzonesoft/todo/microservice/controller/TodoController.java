package com.myzonesoft.todo.microservice.controller;

import com.myzonesoft.todo.microservice.model.Tasks;
import com.myzonesoft.todo.microservice.service.TodoService;
import com.myzonesoft.todo.microservice.util.TodoApplicationConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;

import java.util.List;
import java.util.Optional;

/**
 * TodoController is the Controller class for the To-do Tracker Application.
 * This class is responsible for exposing the REST APIs.
 *
 * CrossOrigin: This is used to accept the requests from cross domain URLs
 *         'http://localhost:3000' = React Frontend Server Domain URL
 *         'http://localhost:4200' = Angular Frontend Server Domain URL
 */
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RequestMapping("/tasks")
@RestController
@SuppressWarnings("unused")
public class TodoController implements TodoApplicationConstants {

    //Variable declarations for logging
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
     * @return Response Entity with Http Status Code and
     *         List of all To-do task objects
     */
    @GetMapping
    public ResponseEntity<List<Tasks>> getAllTasks() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        try {
            List<Tasks> tasksList = todoService.findAll();
            LOGGER.debug("TodoList=="+tasksList.toString());
            return new ResponseEntity<>(tasksList, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        }
    }

    /**
     * Method for listing an item of the To-do task based on id
     * This method accepts HTTP_REQUEST_METHOD:GET
     *
     * @param id Unique identifier of the to-do task
     * @return Response Entity with Http Status Code and
     *         Single To-do task object, based on Id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Tasks> getTasksById(@PathVariable("id") long id) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        Optional<Tasks> task = todoService.findById(id);
        if (task.isPresent()) {
            LOGGER.debug("TodoItem=="+task);
            LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        } else {
            LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Method for deleting an item from the To-do tasks based on Id
     * This method accepts HTTP_REQUEST_METHOD:DELETE
     *
     * @param id Unique identifier of the to-do task to be deleted
     * @return Response Entity with Http Status Code
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTaskById(@PathVariable("id") long id) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        try {
            if(todoService.deleteById(id))
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        }
    }

    /**
     * Method for updating an item of the To-do task
     * This method accepts HTTP_REQUEST_METHOD:PUT
     *
     * @param task To-do task object to be updated
     * @return Response Entity with Http Status Code and
     *         Updated to-do task object
     */
    @PutMapping
    public ResponseEntity<Tasks> updateTaskItem(@RequestBody Tasks task) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        task = todoService.createOrUpdate(task);
        LOGGER.debug("TodoItem=="+task.toString());
        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    /**
     * Method for creating an item in the To-do task
     * This method accepts HTTP_REQUEST_METHOD:POST
     *
     * @param task To-do task object to be created
     * @return Response Entity with Http Status Code and
     *         Newly created To-do task object
     */
    @PostMapping
    public ResponseEntity<Tasks> createTodoTask(@RequestBody Tasks task) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        task = todoService.createOrUpdate(task);
        LOGGER.debug("TodoItem=="+task.toString());
        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    /**
     * Method for listing all the items of the Status variable
     * This method accepts HTTP_REQUEST_METHOD:GET
     *
     * @return Response Entity with Http Status Code and
     *         List of all values of the Task Status variable
     */
    @GetMapping("/status")
    public ResponseEntity<List<String>> getTaskStatus() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        List<String> taskStatusList = todoService.getTodoStatusAsList();
        LOGGER.debug("taskStatusList=="+taskStatusList);
        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return new ResponseEntity<>(taskStatusList, HttpStatus.OK);
    }
}
