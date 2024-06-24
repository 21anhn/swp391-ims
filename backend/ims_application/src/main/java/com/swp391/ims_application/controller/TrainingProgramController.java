package com.swp391.ims_application.controller;

import com.swp391.ims_application.payload.AccountDTO;
import com.swp391.ims_application.payload.InternDashboardDTO;
import com.swp391.ims_application.payload.ReportByAverageScoreDTO;
import com.swp391.ims_application.payload.TrainingProgramDTO;
import com.swp391.ims_application.service.ReportService;
import com.swp391.ims_application.service.imp.ITrainingProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/training-program")
public class TrainingProgramController {

    @Autowired
    private ITrainingProgramService trainingProgramService;

    @Autowired
    private ReportService reportService;

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

    @PostMapping("/{programId}/add-intern/{internId}")
    public ResponseEntity<?> addInternToTrainingProgram(@PathVariable int programId, @PathVariable int internId) {
        boolean check = trainingProgramService.addInternToTrainingProgram(programId, internId);
        if (check) {
            return new ResponseEntity<>("Successfully added intern to training program!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to add intern to training program!", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{programId}/remove-intern/{internId}")
    public ResponseEntity<?> removeInternFromTrainingProgram(@PathVariable int programId, @PathVariable int internId) {
        boolean check = trainingProgramService.removeInternFromTrainingProgram(programId, internId);
        if (check) {
            return new ResponseEntity<>("Successfully removed intern from training program!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to remove intern from training program!", HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/{programId}/interns")
    public ResponseEntity<?> getAllInternsInTrainingProgram(@PathVariable int programId) { //format line new
        List<AccountDTO> internDTOs = trainingProgramService.getAllInternsInTrainingProgram(programId);
        if (!internDTOs.isEmpty()) {
            return new ResponseEntity<>(internDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<>("No interns found in the training program!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/average-score/{programId}")
    public ResponseEntity<List<ReportByAverageScoreDTO>> getAverageScoreReport(@PathVariable("programId") int programId) {
        List<ReportByAverageScoreDTO> reportResponses = reportService.generateAverageScoreReport(programId);
        return ResponseEntity.ok(reportResponses);
    }

    @GetMapping("/{programId}/intern/{internId}/task-completion")
    public ResponseEntity<?> getTaskCompletionForIntern(@PathVariable int programId, @PathVariable int internId) {
        long tasksCompleted = trainingProgramService.getTasksCompletedByIntern(programId, internId);
        long totalTasks = trainingProgramService.getTotalTasksForIntern(programId, internId);

        if (totalTasks == 0) {
            return ResponseEntity.ok("No tasks assigned to this intern in the training program.");
        }

        double completionRate = (double) tasksCompleted / totalTasks * 100;

        Map<String, Object> response = new HashMap<>();
        response.put("tasksCompleted", tasksCompleted);
        response.put("totalTasks", totalTasks);
        response.put("completionRate", completionRate);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/intern/{internId}/program-count")
    public ResponseEntity<?> getTrainingProgramCountByInternId(@PathVariable int internId) {
        long programCount = trainingProgramService.countTrainingProgramsByInternId(internId);
        String message = "Intern with ID " + internId + " is participating in " + programCount + " training program(s).";
        Map<String, Object> response = new HashMap<>();
        response.put("programCount", programCount);
        response.put("message", message);

        return ResponseEntity.ok(response);
    }
}
