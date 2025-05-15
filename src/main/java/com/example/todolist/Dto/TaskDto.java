package com.example.todolist.Dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing a task.
 * This class is used to encapsulate task-related data and transfer it between different layers of the application.
 */

public class TaskDto {
    private final Long id;
    private boolean completed;
    private String title;
    private String content;


    public TaskDto(Long id, String title,boolean completed, String content) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.content = content;

    }

    public Long getId() {
        return id;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(boolean completed){
        this.completed = completed;
    }
    public boolean getCompleted(){
        return completed;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }}
