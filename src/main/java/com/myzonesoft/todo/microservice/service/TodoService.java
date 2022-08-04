package com.myzonesoft.todo.microservice.service;

import com.myzonesoft.todo.microservice.model.Tasks;
import com.myzonesoft.todo.microservice.model.TodoTaskComments;
import com.myzonesoft.todo.microservice.repository.TasksRepository;
import com.myzonesoft.todo.microservice.repository.TodoTaskCommentsRepository;
import com.myzonesoft.todo.microservice.util.TodoApplicationConstants;

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
public class TodoService implements TodoApplicationConstants {

    //Variable declarations
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoService.class);
    private final String className = this.getClass().getSimpleName();

    //Autowired the JPA Repository
    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private TodoTaskCommentsRepository todoTaskCommentsRepository;

    /**
     * Method implementation for listing all the items of the To-do tasks
     * @return List of all To-do task objects
     */
    public List<Tasks> findAll() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        List<Tasks> tasksList = tasksRepository.findAll();
        LOGGER.debug("Tasks list=="+tasksList);
        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return tasksList;
    }

    /**
     * Method implementation for fetching the single To-do task object, based on Id
     * @param id Unique identifier of the to-do task
     * @return Single To-do task object, based on Id
     */
    public Optional<Tasks> findById(long id) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        Optional<Tasks> task = tasksRepository.findById(id);
        LOGGER.debug("Task=="+task);
        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return task;
    }

    /**
     * Method implementation for deleting an item from the To-do tasks based on Id
     * @param id Unique identifier of the to-do task to be deleted
     * @return True or False
     */
    public boolean deleteById(long id) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        boolean isDeleted = false;
        try {
            //Delete the TaskComments before deleting the tasks.
            Optional<Tasks> deletionTask = findById(id);
            if(deletionTask.isPresent()) {
                Set<TodoTaskComments> todoTaskCommentsSet = deletionTask.get().getTodoTaskCommentsSet();
                if (todoTaskCommentsSet != null) {
                    for (TodoTaskComments todoTaskComments : todoTaskCommentsSet) {
                        todoTaskCommentsRepository.delete(todoTaskComments);
                    }
                    tasksRepository.delete(deletionTask.get());
                    isDeleted = true;
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        LOGGER.debug("isDeleted=="+isDeleted);
        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return isDeleted;
    }

    /**
     * Method implementation for creating or updating an item of the To-do task
     * @param task To-do task object to be updated
     * @return Newly created or updated to-do task object
     */
    public Tasks createOrUpdate(Tasks task) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));

        //Get the list of TodoTaskComments from the Request Body
        Set<TodoTaskComments> todoTaskCommentsSet = task.getTodoTaskCommentsSet();

        //Save the TodoTaskComments before task can be saved
        if(todoTaskCommentsSet != null) {
            for (TodoTaskComments todoTaskComment : todoTaskCommentsSet) {
                if (todoTaskComment != null && !todoTaskComment.getTaskComments().isEmpty()) {
                    todoTaskComment.setTodoTask(task);
                    todoTaskComment.setCreationDate(LocalDate.now());
                    todoTaskCommentsRepository.save(todoTaskComment);
                }
            }
        }

        //Set the Creation Date only during initial creation of the task
        if(task.getCreationDate() == null)
            task.setCreationDate(LocalDate.now());

        task = tasksRepository.save(task);
        LOGGER.debug("Todo item=="+task);
        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return task;
    }

    /**
     * Method implementation for listing all the values of the Status
     * variable from the Enum
     * @return List of all values of the Status variable
     */
    public List<String> getTodoStatusAsList() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));

        List<String> taskStatusList = Stream.of(TASK_STATUS.values()).map(TASK_STATUS::name).collect(Collectors.toList());

        LOGGER.debug("statusList=="+taskStatusList);
        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return taskStatusList;
    }
}
