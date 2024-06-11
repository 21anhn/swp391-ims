package com.swp391.ims_application.controller;

import com.swp391.ims_application.entity.Application;
import com.swp391.ims_application.payload.CustomResponse;
import com.swp391.ims_application.service.imp.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private IApplicationService applicationService;

    @GetMapping
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> updateApplicationStatus(@PathVariable int id, @RequestParam  String status) {
        boolean updated = applicationService.updateApplicationStatus(id, status);
        CustomResponse response = new CustomResponse();
        if (updated) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Status updated successfully");
            response.setSuccess(true);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("Application not found");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
