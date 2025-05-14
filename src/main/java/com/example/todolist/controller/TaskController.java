package com.example.todolist.controller;

import com.example.todolist.Dto.TaskDto;
import com.example.todolist.entities.Task;
import com.example.todolist.repositories.TaskRepository;
import com.example.todolist.services.TaskCreateService;
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
    private final TaskCreateService taskCreateService;

    TaskController(TaskRepository taskRepository1, TaskUpdateService taskUpdateService, TaskCreateService taskCreateService) {
        this.taskRepository = taskRepository1;
        this.taskUpdateService = taskUpdateService;
        this.taskCreateService = taskCreateService;
    }
    @CrossOrigin(origins = "*")
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

    @CrossOrigin(origins = "*")
    @PostMapping("/create-task")
    @ResponseStatus(HttpStatus.CREATED)
    public String createTask(@RequestBody TaskDto taskDto ){
        taskCreateService.createTask(taskDto);
        return "Task has been created";

    }
    @CrossOrigin(origins = "*")
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto ){
        taskUpdateService.updateTask(id, taskDto.getTitle(), taskDto.getContent());

        return "Task updated";
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }


}
