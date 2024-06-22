package com.swp391.ims_application.controller;

import com.swp391.ims_application.payload.TrainingProgramDTO;
import com.swp391.ims_application.payload.UserDTO;
import com.swp391.ims_application.service.imp.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mentor")
public class MentorController {

    @Autowired
    IUserService userService;

    @GetMapping("/{mentorId}")
    public ResponseEntity<?> viewTrainingProgramManageByMentorId(@PathVariable int mentorId) {
        List<TrainingProgramDTO> trainingProgramDTO = userService.getTrainingProgramByMentor(mentorId);
        if (trainingProgramDTO == null) {
            return new ResponseEntity<>("Not found training program by mentor id: " + mentorId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trainingProgramDTO, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<?> getAllMentor() {
        List<UserDTO> userDTOList = userService.getMentorList();
        if (userDTOList != null) {
            return new ResponseEntity<>(userDTOList, HttpStatus.FOUND);
        }
        return new ResponseEntity<>("Not found any mentor in system!", HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<?> specifyMentorToTrainingProgram(@RequestParam int mentorId, @RequestParam int programId) {
        boolean check = userService.specifyMentorToProgram(mentorId, programId);
        if (check) {
            return new ResponseEntity<>("Successfully specified mentor: " + mentorId + " to training program: " + programId, HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed specification mentor!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/assign-task")
    public ResponseEntity<?> assignTaskToIntern(@RequestParam int internId, @RequestParam int taskId) {
        boolean check = userService.assignTaskToIntern(internId, taskId);
        if (check) {
            return new ResponseEntity<>("Successfully assigned task: " + taskId + " to intern: " + internId, HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed assign task to intern!", HttpStatus.BAD_REQUEST);
    }
}
