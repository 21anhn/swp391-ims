package com.swp391.ims_application.controller;

import com.swp391.ims_application.entity.TrainingProgram;
import com.swp391.ims_application.entity.TrainingProgramIntern;
import com.swp391.ims_application.entity.User;
import com.swp391.ims_application.service.TrainingProgramInternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training-program-interns")
public class TrainingProgramInternController {

    @Autowired
    private TrainingProgramInternService tpiService;

    @PostMapping("/add")
    public ResponseEntity<TrainingProgramIntern> addInternToProgram(@RequestBody TrainingProgramIntern tpi) {
        TrainingProgramIntern addedIntern = tpiService.addInternToProgram(tpi.getTrainingProgram(), tpi.getUserIntern());
        return ResponseEntity.status(HttpStatus.CREATED).body(addedIntern);
    }

    @DeleteMapping("/remove/{tpiId}")
    public ResponseEntity<Void> removeInternFromProgram(@PathVariable int tpiId) {
        boolean removed = tpiService.removeInternFromProgram(tpiId);
        if (removed) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all/{programId}")
    public ResponseEntity<List<TrainingProgramIntern>> getAllInternsByProgram(@PathVariable int programId) {
        List<TrainingProgramIntern> interns = tpiService.getAllInternsByProgram(programId);
        return ResponseEntity.ok(interns);
    }
}
