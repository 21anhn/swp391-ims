package com.swp391.ims_application.controller;

import com.swp391.ims_application.payload.TrainingProgramInternFeedbackDTO;
import com.swp391.ims_application.service.TrainingProgramInternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/intern")
public class TrainingProgramInternController {

    @Autowired
    private TrainingProgramInternService trainingProgramInternService;

    @PostMapping("/feedback")
    public ResponseEntity<String> giveFeedback(@RequestBody TrainingProgramInternFeedbackDTO feedbackDTO) {
        trainingProgramInternService.giveFeedback(feedbackDTO);
        return new ResponseEntity<>("Feedback submitted successfully!", HttpStatus.OK);
    }

    @GetMapping("/feedback")
    public ResponseEntity<TrainingProgramInternFeedbackDTO> getFeedback(@RequestParam int internId, @RequestParam int programId) {
        TrainingProgramInternFeedbackDTO feedbackDTO = trainingProgramInternService.getFeedback(internId, programId);
        if (feedbackDTO != null) {
            return new ResponseEntity<>(feedbackDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
