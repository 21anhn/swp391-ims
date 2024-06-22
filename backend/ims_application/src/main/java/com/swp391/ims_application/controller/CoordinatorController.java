package com.swp391.ims_application.controller;

import com.swp391.ims_application.service.imp.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coordinator")
public class CoordinatorController {

    @Autowired
    private IUserService userService;

    @PutMapping
    public ResponseEntity<?> specifyInternToTrainingProgram(@RequestParam int internId, @RequestParam int programId) {
        boolean check = userService.assignInternToTrainingProgram(programId, internId);
        if (check) {
            return new ResponseEntity<>("Successfully assigned intern: " + internId + " to program: " + programId, HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to assign intern: " + internId + " to program: " + programId, HttpStatus.BAD_REQUEST);
    }
}
