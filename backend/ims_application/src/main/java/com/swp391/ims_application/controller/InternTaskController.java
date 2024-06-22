package com.swp391.ims_application.controller;

import com.swp391.ims_application.payload.InternTaskDTO;
import com.swp391.ims_application.service.InternTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/intern-tasks")
public class InternTaskController {

    @Autowired
    private InternTaskService internTaskService;

    @GetMapping("/{itId}")
    public ResponseEntity<InternTaskDTO> viewTask(@PathVariable int itId) {
        InternTaskDTO internTaskDTO = internTaskService.getInternTaskById(itId);
        return ResponseEntity.ok(internTaskDTO);
    }

    @GetMapping("/{itId}/feedback")
    public ResponseEntity<String> viewTaskFeedback(@PathVariable int itId) {
        String feedback = internTaskService.viewTaskFeedback(itId);
        return ResponseEntity.ok(feedback);
    }

    @PutMapping("/{itId}/feedback")
    public ResponseEntity<InternTaskDTO> updateTaskFeedback(@PathVariable int itId, @RequestParam float score, @RequestParam String comment) {
        InternTaskDTO updatedInternTaskDTO = internTaskService.updateInternTaskFeedback(itId, score, comment);
        return ResponseEntity.ok(updatedInternTaskDTO);
    }

    @PostMapping
    public ResponseEntity<InternTaskDTO> createInternTask(@RequestParam int userInternId,
                                                          @RequestParam int taskId,
                                                          @RequestParam float score,
                                                          @RequestParam String comment,
                                                          @RequestParam("file") MultipartFile file) {
        InternTaskDTO internTaskDTO = new InternTaskDTO();
        internTaskDTO.setUserInternId(userInternId);
        internTaskDTO.setTaskId(taskId);
        internTaskDTO.setScore(score);
        internTaskDTO.setComment(comment);

        try {
            InternTaskDTO savedInternTaskDTO = internTaskService.saveInternTask(internTaskDTO, file);
            return ResponseEntity.ok(savedInternTaskDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}


