package com.example.todolist.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Represents a task entity in the TODO list application.
 * This entity is mapped to the "Task" table in the database.
 * It contains information about the task's title, completion status,
 * content, and timestamps for creation and updates.
 * 
 * Fields:
 * - id: The unique identifier for the task (auto-generated).
 * - title: The title or name of the task.
 * - completed: Indicates whether the task is completed (default is false).
 * - content: Additional details or description of the task.
 * - createdAt: The timestamp when the task was created.
 * - updatedAt: The timestamp when the task was last updated.
 * 
 * Constructors:
 * - Default constructor for creating an empty task.
 * - Parameterized constructor for creating a task with specific values.
 * 
 * Getters and Setters:
 * - Provides access and modification methods for all fields.
 */
@Entity
@Table(name = "Task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "completed")
    private  boolean completed = false;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Task() {
    }

    public Task(String title,Boolean completed ,String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.title = title;
        this.completed = completed;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



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
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}