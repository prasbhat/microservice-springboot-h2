package com.myzonesoft.microservice.todo.service;

import com.myzonesoft.microservice.todo.model.Todo;
import com.myzonesoft.microservice.todo.model.TodoTaskComments;
import com.myzonesoft.microservice.todo.repository.TodoRepository;
import com.myzonesoft.microservice.todo.repository.TodoTaskCommentsRepository;
import com.myzonesoft.microservice.todo.util.TodoApplicationConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.text.MessageFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TodoServiceImpl is the service class that implements TodoService interface
 * In this service class data is fetched from database
 */
@Service
public class TodoServiceImpl implements TodoService, TodoApplicationConstants {

    //Variable declarations
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoServiceImpl.class);
    private final String className = this.getClass().getSimpleName();

    //Autowired the JPA Repository
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoTaskCommentsRepository todoTaskCommentsRepository;

    /**
     * Method implementation for listing all the items of the To-do tasks
     * @return List of all items of the To-do tasks
     */
    @Override
    public List<Todo> findAll() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        List<Todo> todoList = todoRepository.findAll();
        LOGGER.debug("Todo list=="+todoList);
        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return todoList;
    }

    /**
     * Method implementation for listing an item of the To-do task based on id
     * @param id Unique identifier of the to-do task
     * @return To-do task based on id
     */
    @Override
    public Todo findById(long id) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        Optional<Todo> todoItem = todoRepository.findById(id);
        LOGGER.debug("Todo item=="+todoItem);
        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return todoItem.orElse(null);
    }

    /**
     * Method implementation for deleting an item from the To-do tasks based on id
     * @param id Unique identifier of the to-do task to be deleted
     * @return True or False
     */
    @Override
    public boolean deleteById(long id) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        boolean isDeleted = false;
        try {
            Set<TodoTaskComments> todoTaskCommentsSet = findById(id).getTodoTaskCommentsSet();
            if(todoTaskCommentsSet != null) {
                for(TodoTaskComments todoTaskComments: todoTaskCommentsSet) {
                    todoTaskCommentsRepository.delete(todoTaskComments);
                }
            }
            todoRepository.deleteById(id);
            isDeleted = true;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        LOGGER.debug("isDeleted=="+isDeleted);
        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return isDeleted;
    }

    /**
     * Method implementation for creating or updating an item of the To-do task
     * @param todoItem To-do task object to be updated
     * @return Newly created or updated to-do task object
     */
    @Override
    public Todo createOrUpdate(Todo todoItem) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        Set<TodoTaskComments> todoTaskCommentsSet = todoItem.getTodoTaskCommentsSet();

        if(todoTaskCommentsSet != null) {
            for (TodoTaskComments todoTaskComments : todoTaskCommentsSet) {
                if (todoTaskComments.getTodoTaskCommentsId() == null && !todoTaskComments.getTaskComments().isEmpty()) {
                    todoTaskComments.setTodoTask(todoItem);
                    todoTaskComments.setCreationDate(LocalDate.now());
                    todoTaskCommentsRepository.save(todoTaskComments);
                }
            }
        }

        todoItem.setCreationDate(LocalDate.now());
        todoItem = todoRepository.save(todoItem);
        LOGGER.debug("Todo item=="+todoItem);
        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return todoItem;
    }

    /**
     * Method implementation for listing all the items of the Status variable
     * @return List of all values of the Status variable
     */
    @Override
    public List<String> getTodoStatusAsList() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));

        List<String> statusList = Stream.of(TODO_STATUS.values()).map(TODO_STATUS::name).collect(Collectors.toList());
        LOGGER.debug("statusList=="+statusList);
        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return statusList;
    }
}
