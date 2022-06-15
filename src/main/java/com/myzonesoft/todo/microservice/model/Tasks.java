package com.myzonesoft.todo.microservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.time.LocalDate;
import java.util.Set;

/**
 * To-do POJO model class
 */
@Entity
@SuppressWarnings("unused")
public class Tasks {
    /**
     * Unique Identifier for the To-do task
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long systemTasksId;
    /**
     * Title for the To-do task
     */
    private String title;
    /**
     * Description for the To-do task
     */
    private String description;
    /**
     * Creation date- System generated
     */
    private LocalDate creationDate;
    /**
     * Due date for the To-do task
     */
    private LocalDate dueDate;
    /**
     * Status of the To-do task
     */
    private String status;

    @JsonManagedReference
    @OneToMany(mappedBy = "todoTask", fetch = FetchType.EAGER)
    private Set<TodoTaskComments> todoTaskCommentsSet;

    //Constructors

    /**
     * Default Constructor without any parameters
     */
    public Tasks() {
    }

    /**
     * Constructor with all parameters
     * @param systemTasksId Unique Identifier for the To-do task
     * @param title Title for the To-do task
     * @param description Description for the To-do task
     * @param dueDate Due date for the To-do task
     * @param status Status of the To-do task
     * @param creationDate System generated creation date of the to do task
     * @param todoTaskCommentsSet Comments related to the to do task
     */
    public Tasks(Long systemTasksId, String title, String description, LocalDate creationDate, LocalDate dueDate, String status, Set<TodoTaskComments> todoTaskCommentsSet) {
        this.systemTasksId = systemTasksId;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.status = status;
        this.todoTaskCommentsSet = todoTaskCommentsSet;
    }

    //Getters and setters for all the private variables declared above

    public Long getSystemTasksId() {
        return systemTasksId;
    }

    public void setSystemTasksId(Long systemTasksId) {
        this.systemTasksId = systemTasksId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Set<TodoTaskComments> getTodoTaskCommentsSet() {
        return todoTaskCommentsSet;
    }

    public void setTodoTaskCommentsSet(Set<TodoTaskComments> todoTaskCommentsSet) {
        this.todoTaskCommentsSet = todoTaskCommentsSet;
    }

    /**
     * Method for displaying the Task POJO class as a String
     *
     * @return String To-do POJO object
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tasks{");
        sb.append("systemTasksId=").append(systemTasksId);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", creationDate=").append(creationDate);
        sb.append(", dueDate=").append(dueDate);
        sb.append(", status='").append(status).append('\'');
        sb.append(", todoTaskCommentsSet=").append(todoTaskCommentsSet);
        sb.append('}');
        return sb.toString();
    }
}
