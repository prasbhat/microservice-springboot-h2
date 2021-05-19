package com.myzonesoft.microservice.todo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDate;

/**
 * To-do POJO model class
 */
@Entity
@SuppressWarnings("unused")
public class Todo {
    /**
     * Unique Identifier for the To-do task
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Title for the To-do task
     */
    private String title;
    /**
     * Description for the To-do task
     */
    private String description;
    /**
     * Due date for the To-do task
     */
    private LocalDate dueDate;
    /**
     * Status of the To-do task
     */
    private String status;

    //Constructors

    /**
     * Constructor with all parameters
     * @param id Unique Identifier for the To-do task
     * @param title Title for the To-do task
     * @param description Description for the To-do task
     * @param dueDate Due date for the To-do task
     * @param status Status of the To-do task
     */
    public Todo(Long id, String title, String description, LocalDate dueDate, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    //Getters and setters for all the private variables declared above

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    /**
     * toString method for displaying the To-do POJO class as a String
     * @return String To-do POJO object
     */
    @Override
    public String toString() {
        return "Todo{" + "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", status='" + status + '\'' +
                '}';
    }
}
