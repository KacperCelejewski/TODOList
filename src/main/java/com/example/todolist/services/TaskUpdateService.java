package com.example.todolist.services;

import com.example.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TaskUpdateService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskUpdateService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @Transactional
    public void updateTask(Long id, String newTitle, String newContent) {
        taskRepository.findById(id).ifPresentOrElse(task -> {
            task.setTitle(newTitle);
            task.setContent(newContent);
            task.setUpdatedAt(LocalDateTime.now());
            taskRepository.save(task);
        }, () -> {
            throw new EmptyResultDataAccessException("Task not found with id: " + id, 1);
        });
    }
}