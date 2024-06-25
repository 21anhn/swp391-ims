package com.swp391.ims_application.controller;

import com.swp391.ims_application.payload.*;
import com.swp391.ims_application.service.ReportService;
import com.swp391.ims_application.service.imp.IEducationalResourceService;
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
    private IEducationalResourceService educationalResourceService;

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
        Map<String, Object> response = new HashMap<>();
        response.put("internId", internId);
        response.put("programCount", programCount);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/{programId}/educational-resource")
    public ResponseEntity<?> createEducationalResource(@PathVariable int programId, @RequestBody EducationalResourceDTO resourceDTO) {
        resourceDTO.setTrainingProgramId(programId);
        resourceDTO.setAvailable(true);
        boolean created = educationalResourceService.createEducationalResource(resourceDTO);
        if (created) {
            return new ResponseEntity<>("Educational resource created successfully!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed to create educational resource!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{programId}/educational-resource")
    public ResponseEntity<?> editEducationalResource(@PathVariable int programId, @RequestBody EducationalResourceDTO resourceDTO) {
        resourceDTO.setTrainingProgramId(programId);
        boolean check = educationalResourceService.updateEducationalResource(resourceDTO);
        if (check) {
            return new ResponseEntity<>("Successfully edited educational resource!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to edit educational resource!", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{programId}/educational-resource/{resourceId}")
    public ResponseEntity<?> removeEducationalResource(@PathVariable int programId, @PathVariable int resourceId) {
        boolean removed = educationalResourceService.removeEducationalResourceFromProgram(resourceId, programId);
        if (removed) {
            return new ResponseEntity<>("Educational resource removed successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to remove educational resource!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/educational-resource")
    public ResponseEntity<?> getAllEducationalResources() {
        List<EducationalResourceDTO> resources = educationalResourceService.getAllEducationalResources();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/educational-resource/{resourceId}")
    public ResponseEntity<?> getEducationalResourceById(@PathVariable int resourceId) {
        EducationalResourceDTO resource = educationalResourceService.getEducationalResourceById(resourceId);
        if (resource != null) {
            return new ResponseEntity<>(resource, HttpStatus.OK);
        }
        return new ResponseEntity<>("Educational resource not found with id: " + resourceId, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{programId}/mentor-educational-resources")
    public ResponseEntity<?> getEducationalResourcesByMentor(@PathVariable int programId) {
        List<EducationalResourceDTO> resources = educationalResourceService.getEducationalResourcesByMentorId(programId);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }
}
