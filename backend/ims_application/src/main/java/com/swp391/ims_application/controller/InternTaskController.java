package com.swp391.ims_application.controller;

import com.swp391.ims_application.entity.InternTask;
import com.swp391.ims_application.payload.FeedbackDTO;
import com.swp391.ims_application.service.FileService;
import com.swp391.ims_application.service.InternTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/intern-tasks")
public class InternTaskController {

    @Autowired
    private FileService fileService;

    @Autowired
    private InternTaskService internTaskService;

    @PostMapping("/{id}/upload")
    public ResponseEntity<String> uploadFile(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return new ResponseEntity<>("Please select a file!", HttpStatus.BAD_REQUEST);
        }

        try {
            // Call the service to upload the file
            String fileUrl = fileService.uploadFile(file);

            // Find the InternTask by id
            InternTask internTask = internTaskService.findInternTaskById(id);
            if (internTask == null) {
                return new ResponseEntity<>("InternTask not found!", HttpStatus.NOT_FOUND);
            }

            // Set the file URL and save the InternTask
            internTask.setFileUrl(fileUrl);
            internTaskService.saveInternTask(internTask);

            return new ResponseEntity<>("File uploaded successfully: " + fileUrl, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Could not upload the file: " + file.getOriginalFilename() + "!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/mentorFeedback/{taskId}")
    public ResponseEntity<FeedbackDTO> getMentorFeedback(@PathVariable int taskId) {
        FeedbackDTO feedbackDTO = internTaskService.getMentorFeedbackByTaskId(taskId);
        if (feedbackDTO != null) {
            return new ResponseEntity<>(feedbackDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
