package com.swp391.ims_application.controller;

import com.swp391.ims_application.entity.Application;
import com.swp391.ims_application.payload.ApplicationResponse;
import com.swp391.ims_application.service.imp.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @GetMapping("/{campaignId}")
    public ResponseEntity<?> getApplicationsByCampaignId(@PathVariable int campaignId) {
        List<ApplicationResponse> applicationResponses = applicationService.getApplicationResponsesByCampaignId(campaignId);
        if (applicationResponses != null) {
            return new ResponseEntity<>(applicationResponses, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found any applications with campaign id: " + campaignId, HttpStatus.NOT_FOUND);
    }


}
