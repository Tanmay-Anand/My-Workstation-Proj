package com.tanmay.my_workstation_backend.controller;

import com.tanmay.my_workstation_backend.domain.Task;
import com.tanmay.my_workstation_backend.dto.TaskRequest;
import com.tanmay.my_workstation_backend.dto.TaskResponse;
import com.tanmay.my_workstation_backend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> create(@Valid @RequestBody TaskRequest req) {
        TaskResponse resp = taskService.createTask(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponse>> list(
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "status", required = false) Task.Status status,
            @RequestParam(value = "priority", required = false) Task.Priority priority,
            @RequestParam(value = "dueBefore", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueBefore,
            @RequestParam(value = "dueAfter", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueAfter,
            @PageableDefault(size = 20) Pageable pageable) {

        Page<TaskResponse> page = taskService.listTasks(q, status, priority, dueBefore, dueAfter, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> get(@PathVariable Long id) {
        TaskResponse resp = taskService.getTask(id);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable Long id, @Valid @RequestBody TaskRequest req) {
        TaskResponse resp = taskService.updateTask(id, req);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<TaskResponse> complete(@PathVariable Long id) {
        TaskResponse resp = taskService.markCompleted(id);
        return ResponseEntity.ok(resp);
    }
}
