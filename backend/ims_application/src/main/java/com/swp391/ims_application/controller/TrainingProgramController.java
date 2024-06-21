package com.swp391.ims_application.controller;

import com.swp391.ims_application.entity.TrainingProgram;
import com.swp391.ims_application.payload.TrainingProgramRequest;
import com.swp391.ims_application.service.TrainingProgramServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/training-programs")
public class TrainingProgramController {
    @Autowired
    private TrainingProgramServiceImp trainingProgramService;

    @PostMapping
    public TrainingProgram createTrainingProgram(@RequestBody TrainingProgramRequest request) {
        return trainingProgramService.createTrainingProgram(request);
    }

    @GetMapping("/{id}")
    public TrainingProgram getTrainingProgramById(@PathVariable int id) {
        return trainingProgramService.getTrainingProgramById(id);
    }

    @PutMapping("/{id}")
    public TrainingProgram updateTrainingProgram(@PathVariable int id, @RequestBody TrainingProgramRequest request) {
        return trainingProgramService.updateTrainingProgram(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTrainingProgram(@PathVariable int id) {
        trainingProgramService.deleteTrainingProgram(id);
    }

    @PostMapping("/{programId}/mentors/{mentorId}")
    public void assignMentorToTrainingProgram(@PathVariable int programId, @PathVariable int mentorId) {
        trainingProgramService.assignMentorToTrainingProgram(programId, mentorId);
    }
}
