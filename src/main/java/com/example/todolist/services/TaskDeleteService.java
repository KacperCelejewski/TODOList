package com.example.todolist.services;

import com.example.todolist.entities.Task;
import com.example.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskDeleteService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskDeleteService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @Transactional
    public void deleteTask(Long id){
        taskRepository.findById(id).ifPresentOrElse( task -> {
            taskRepository.delete(task);
        }, () -> {
            throw new EmptyResultDataAccessException("Task not found with id: " + id, 1);
        });
    }
}
