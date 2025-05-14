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



    @Transactional
    public void createTask(TaskDto taskDto) {
        Task task = new Task(
                taskDto.getTitle(),
                taskDto.getContent(),
                LocalDateTime.now(),
                LocalDateTime.now()

        );
        Task savedTask = taskRepository.save(task);
    }
}
