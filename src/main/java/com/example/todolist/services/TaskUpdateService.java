package com.example.todolist.services;

import com.example.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TaskUpdateService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskUpdateService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
        /**
     * Updates the task with the specified ID. Allows updating the title, completion status,
     * and content of the task. If the task is found, the specified fields are updated and
     * the task's updated timestamp is set to the current time. If the task is not found,
     * an exception is thrown.
     *
     * @param id         the ID of the task to update
     * @param newTitle   an optional new title for the task
     * @param completed  an optional new completion status for the task
     * @param newContent an optional new content for the task
     * @throws EmptyResultDataAccessException if no task is found with the given ID
     */
    @Transactional
    public void updateTask(Long id, Optional<String> newTitle, Optional<Boolean>completed, Optional<String> newContent) {
        taskRepository.findById(id).ifPresentOrElse(task -> {
            newTitle.ifPresent(task::setTitle);
            completed.ifPresent(task::setCompleted);
            newContent.ifPresent(task::setContent);
            task.setUpdatedAt(LocalDateTime.now());
            taskRepository.save(task);
        }, () -> {
            throw new EmptyResultDataAccessException("Task not found with id: " + id, 1);
        });
    }
}