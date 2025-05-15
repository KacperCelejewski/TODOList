package com.example.todolist.repositories;

import com.example.todolist.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Task} entities.
 * Extends the {@link JpaRepository} interface to provide CRUD operations
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
}
