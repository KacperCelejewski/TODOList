package com.example.todolist.services;

import com.example.todolist.Dto.TaskDto;
import com.example.todolist.entities.Task;
import com.example.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TaskCreateService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskCreateService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    /**
     * Creates a new task with the provided details. The task is saved to the repository.
     *
     * @param taskDto the data transfer object containing task details
     */
    



    @Transactional
    public void createTask(TaskDto taskDto) {
        Task task = new Task(
                taskDto.getTitle(),
                taskDto.getCompleted(),
                taskDto.getContent(),
                LocalDateTime.now(),
                LocalDateTime.now()

        );

    Task savedTask = taskRepository.save(task);
    

    }
}
