package org.alexkolo.simpleweb.exception;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException() {
        throw new RuntimeException("Task not found");
    }

}