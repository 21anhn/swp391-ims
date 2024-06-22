package com.swp391.ims_application.controller;

import com.swp391.ims_application.payload.TrainingProgramDTO;
import com.swp391.ims_application.service.TrainingProgramService;
import com.swp391.ims_application.service.imp.ITrainingProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training-program")
public class TrainingProgramController {

    @Autowired
    private ITrainingProgramService trainingProgramService;

    @GetMapping
    public ResponseEntity<?> getAllTrainingProgram() {
        List<TrainingProgramDTO> trainingProgramDTOS = trainingProgramService.getAllTrainingPrograms();
        if (trainingProgramDTOS.isEmpty()) {
            return new ResponseEntity<>("Not found any training program!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trainingProgramDTOS, HttpStatus.OK);
    }

    @GetMapping("/{programId}")
    public ResponseEntity<?> getProgramById(@PathVariable int programId) {
        TrainingProgramDTO trainingProgramDTO = trainingProgramService.getTrainingProgramById(programId);
        if (trainingProgramDTO.getProgramId() == programId) {
            return new ResponseEntity<>(trainingProgramDTO, HttpStatus.FOUND);
        }
        return new ResponseEntity<>("Not found any training program with id: " + programId, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createTrainingProgram(@RequestBody TrainingProgramDTO trainingProgramDTO) {
        boolean check = trainingProgramService.createTrainingProgram(trainingProgramDTO);
        if(check) {
            return new ResponseEntity<>("Successfully created training program!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Falied creation training program!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{programId}")
    public ResponseEntity<?> editTrainingProgram(@PathVariable int programId, @RequestBody TrainingProgramDTO trainingProgramDTO) {
        boolean check = trainingProgramService.editTrainingProgram(programId, trainingProgramDTO);
        if(check) {
            return new ResponseEntity<>("Successfully edited training program!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Falied edition training program!", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{programId}")
    public ResponseEntity<?> deleteTrainingProgram(@PathVariable int programId) {
        boolean check = trainingProgramService.deleteTrainingProgram(programId);
        if(check) {
            return new ResponseEntity<>("Successfully edited training program!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Falied edition training program!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{programId}/average-score")
    public ResponseEntity<?> calculateAverageScore(@PathVariable int programId) {
        double averageScore = trainingProgramService.calculateAverageScore(programId);

        TrainingProgramDTO trainingProgramDTO = trainingProgramService.getTrainingProgramById(programId);
        String responseMessage = "Average score for intern in training program id " +  + programId + " is: " + averageScore;

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @DeleteMapping("/{programId}/intern/{internId}")
    public ResponseEntity<String> removeInternFromTrainingProgram(@PathVariable int programId, @PathVariable int internId) {
        boolean removed = trainingProgramService.removeInternFromTrainingProgram(programId, internId);
        if (removed) {
            return ResponseEntity.ok("Intern removed from training program successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Intern or Training Program not found.");
        }
    }
}
