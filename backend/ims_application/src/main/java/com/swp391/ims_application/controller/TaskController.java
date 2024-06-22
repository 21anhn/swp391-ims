package com.swp391.ims_application.controller;

import com.swp391.ims_application.payload.TaskCompletionDTO;
import com.swp391.ims_application.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/completion")
    public ResponseEntity<TaskCompletionDTO> getTaskCompletion(
            @RequestParam int programId,
            @RequestParam int internId) {

        try {
            TaskCompletionDTO taskCompletion = taskService.getTaskCompletionForIntern(programId, internId);
            return ResponseEntity.ok(taskCompletion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
