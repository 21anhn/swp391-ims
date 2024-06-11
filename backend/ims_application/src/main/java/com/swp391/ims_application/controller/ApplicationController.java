package com.swp391.ims_application.controller;

import com.swp391.ims_application.entity.Application;
import com.swp391.ims_application.payload.ApplicationResponse;
import com.swp391.ims_application.payload.CreateApplicationRequest;
import com.swp391.ims_application.service.imp.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public ResponseEntity<Application> createApplication(@RequestBody CreateApplicationRequest createApplicationRequest) {
        Application newApplication = applicationService.createApplication(createApplicationRequest);
        return ResponseEntity.ok(newApplication);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApplicationResponse> updateApplicationStatus(@PathVariable int id, @RequestBody ApplicationResponse updateRequest) {
        Application updatedApplication = applicationService.updateApplicationStatus(id, updateRequest.getStatus());
        if (updatedApplication != null) {
            ApplicationResponse applicationResponse = convertToApplicationResponse(updatedApplication);
            return ResponseEntity.ok(applicationResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private ApplicationResponse convertToApplicationResponse(Application updatedApplication) {
        return null;
    }
}
