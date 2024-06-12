package com.todosimple.controllers;

import com.todosimple.models.Task;
import com.todosimple.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {
        Task task = taskService.findById(id);
        return ResponseEntity.ok().body(task);
    }

    @GetMapping("/users/{userID}")
    public ResponseEntity<List<Task>> findAllByUserId(@PathVariable Long userID) {
        List<Task> tasks = taskService.findAllByUserId(userID);
        return ResponseEntity.ok().body(tasks);
    }

    @PostMapping()
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody Task task) {
        Task newTask = taskService.create(task);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(task.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Task> update(@Valid @PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        this.taskService.update(task);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> delete(@PathVariable Long id) {
        this.taskService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
