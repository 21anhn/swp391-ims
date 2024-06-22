package com.swp391.ims_application.controller;

import com.swp391.ims_application.entity.Task;
import com.swp391.ims_application.payload.TaskDTO;
import com.swp391.ims_application.service.imp.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private ITaskService taskService;

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskDTO taskDTO) {
        Task task = taskService.createTask(taskDTO);
        if (task != null) {
            return new ResponseEntity<>("Successfully created task!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed creation task!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody TaskDTO taskDTO) {
        Task task = taskService.updateTask(id, taskDTO);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{id}/check-availability")
    public ResponseEntity<?> checkAndUpdateAvailability(@PathVariable int id) {
        taskService.checkAndUpdateTaskAvailability(id);
        return ResponseEntity.ok("Task availability updated if necessary");
    }
}
