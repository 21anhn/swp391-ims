package com.swp391.ims_application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swp391.ims_application.entity.Application;
import com.swp391.ims_application.payload.ApplicationDTO;
import com.swp391.ims_application.service.imp.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin("*")
public class ApplicationController {

    @Autowired
    private IApplicationService applicationService;

    @GetMapping
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/{campaignId}")
    public ResponseEntity<?> getApplicationsByCampaignId(@PathVariable int campaignId) {
        List<ApplicationDTO> applicationResponses = applicationService.getApplicationResponsesByCampaignId(campaignId);
        if (applicationResponses != null) {
            return new ResponseEntity<>(applicationResponses, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found any applications with campaign id: " + campaignId, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{campaignId}")
    public ResponseEntity<?> createApplication(@PathVariable int campaignId, @RequestPart("application") String applicationJson,
                                               @RequestPart("cv") MultipartFile cv) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ApplicationDTO applicationDTO = objectMapper.readValue(applicationJson, ApplicationDTO.class);

        boolean check = applicationService.createApplication(applicationDTO, campaignId, cv);
        if (check) {
            return new ResponseEntity<>("Successfully created application!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed to create application!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{applicationId}")
    public ResponseEntity<?> getApplicationById(@PathVariable int applicationId, @RequestParam String status) {
        boolean check = applicationService.updateStatus(applicationId, status);
        if (check) {
            return new ResponseEntity<>("Successfully updated status!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Update failed!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{applicationId}/chooseDate")
    public ResponseEntity<?> updateInterviewDateInApplication(@PathVariable int applicationId, @RequestBody ApplicationDTO applicationDTO) {
        boolean check = applicationService.updateInterviewDateInApplication(applicationId, applicationDTO);
        if (check) {
            return new ResponseEntity<>("Successfully updated interview date in application!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Update interview date failed due to not exist email: " + applicationDTO.getEmail() + "!", HttpStatus.BAD_REQUEST);
    }

}
