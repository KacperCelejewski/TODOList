package com.example.todolist.controller;

import com.example.todolist.Dto.TaskDto;
import com.example.todolist.entities.Task;
import com.example.todolist.repositories.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository1) {
        this.taskRepository = taskRepository1;
    }
    @GetMapping
    public List<TaskDto> getTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> new TaskDto(
                        task.getId(),
                        task.getTitle(),
                        task.getContent(),
                        task.getCreatedAt()))
                .collect(Collectors.toList());
    }
    @PostMapping("/create-task")
    @ResponseStatus(HttpStatus.CREATED)
    public String createTask(@RequestBody TaskDto taskDto ){
        Task task = new Task(
                taskDto.getTitle(),
                taskDto.getContent(),
                LocalDateTime.now(),
                LocalDateTime.now()

        );
        Task savedTask = taskRepository.save(task);
                return "ok";

    }


}
