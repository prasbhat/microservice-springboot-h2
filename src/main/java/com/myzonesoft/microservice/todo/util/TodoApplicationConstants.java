package com.myzonesoft.microservice.todo.util;

public interface TodoApplicationConstants {
    //Dynamic Statements
    String LOGGER_ENTRY = "Entry [{0}::{1}]";
    String LOGGER_EXIT = "Exit [{0}::{1}]";

    //Enums
    enum TODO_STATUS {NOT_STARTED, IN_PROGRESS, ON_HOLD, COMPLETED, DEFERRED}
}
