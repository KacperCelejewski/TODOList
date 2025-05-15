package com.example.todolist.services;


import com.example.todolist.repositories.TaskRepository;
import exception.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskDeleteService {
    /**
     * Service class responsible for deleting tasks.
     * This service interacts with the TaskRepository to remove task data. 
     **/
    private final TaskRepository taskRepository;

    @Autowired
    public TaskDeleteService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    /**
     * Deletes the task with the specified ID. If the task is found, it is removed from the repository.
     * If the task is not found, an exception is thrown.
     *
     * @param id the ID of the task to delete
     * @throws EmptyResultDataAccessException if no task is found with the given ID
     */
    @Transactional
    public void deleteTask(Long id){
        taskRepository.findById(id).ifPresentOrElse( task -> {
            taskRepository.delete(task);
        }, () -> {
            throw new TaskNotFoundException("Task not found with id: " + id);
        });
    }
}
