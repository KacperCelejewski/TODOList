package com.example.todolist.controller;

import com.example.todolist.Dto.TaskDto;
import com.example.todolist.entities.Task;
import com.example.todolist.repositories.TaskRepository;
import com.example.todolist.services.TaskCreateService;
import com.example.todolist.services.TaskUpdateService;
import exception.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * TaskController is a REST controller that provides endpoints for managing tasks.
 * It supports operations such as retrieving all tasks, creating a new task, 
 * updating an existing task, and deleting a task.
 * 
 * Endpoints:
 * - GET /api/tasks: Retrieves a list of all tasks.
 * - POST /api/tasks/create-task: Creates a new task.
 * - PUT /api/tasks/update/{id}: Updates an existing task by its ID.
 * - DELETE /api/tasks/delete/{id}: Deletes a task by its ID.
 * 
 * Dependencies:
 * - TaskRepository: Handles database operations for tasks.
 * - TaskUpdateService: Provides logic for updating tasks.
 * - TaskCreateService: Provides logic for creating tasks.
 * 
 * Cross-Origin Resource Sharing (CORS):
 * - Allows requests from any origin.
 * 
 * Exception Handling:
 * - Returns appropriate HTTP status codes and messages for errors such as task not found 
 *   or internal server errors.
 */
@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
class TaskController {
    private final TaskRepository taskRepository;
    private final TaskUpdateService taskUpdateService;
    private final TaskCreateService taskCreateService;

    TaskController(TaskRepository taskRepository1, TaskUpdateService taskUpdateService, TaskCreateService taskCreateService) {
        this.taskRepository = taskRepository1;
        this.taskUpdateService = taskUpdateService;
        this.taskCreateService = taskCreateService;
    }

    @GetMapping
    public List<TaskDto> getTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> new TaskDto(
                        task.getId(),
                        task.getTitle(),
                        task.getCompleted(),
                        task.getContent()
                        ))
                .collect(Collectors.toList());
    }

    @PostMapping("/create-task")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createTask(@RequestBody TaskDto taskDto ) {
        try {
            taskCreateService.createTask(taskDto);
        return ResponseEntity.ok("Task created successfully.");
    } catch (Exception e){
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create task");
    }}

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto ){
        try {
            taskUpdateService.updateTask(
                    id,
                    Optional.ofNullable(taskDto.getTitle()),
                    Optional.ofNullable(taskDto.getCompleted()),
                    Optional.ofNullable(taskDto.getContent())
            );
            return ResponseEntity.ok("Task updated successfully.");
        } catch (TaskNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update task.");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        if (!taskRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
        }
        taskRepository.deleteById(id);
        return ResponseEntity.ok("Task deleted successfully.");
    }


}
