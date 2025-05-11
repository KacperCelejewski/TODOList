package com.example.todolist.controller;

import com.example.todolist.Dto.TaskDto;
import com.example.todolist.entities.Task;
import com.example.todolist.repositories.TaskRepository;
import com.example.todolist.services.TaskUpdateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
class TaskController {
    private final TaskRepository taskRepository;
    private final TaskUpdateService taskUpdateService;

    public TaskController(TaskRepository taskRepository1, TaskUpdateService taskUpdateService) {
        this.taskRepository = taskRepository1;
        this.taskUpdateService = taskUpdateService;
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
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto ){
        taskUpdateService.updateTask(id, taskDto.getTitle(), taskDto.getContent());

        return "Task updated";
    }



}
