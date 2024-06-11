package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.Application;
import com.swp391.ims_application.payload.ApplicationResponse;
import com.swp391.ims_application.payload.CreateApplicationRequest;
import com.swp391.ims_application.repository.ApplicationRepository;
import com.swp391.ims_application.repository.InternshipCampaignRepository;
import com.swp391.ims_application.service.imp.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService implements IApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private InternshipCampaignRepository internshipCampaignRepository;

    @Override
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public List<Application> getApplicationsByCampaignId(int campaignId) {
        List<Application> applications = applicationRepository.findByInternshipCampaignCampaignId(campaignId);
        return applications.isEmpty() ? null : applications;
    }

    @Override
    public List<ApplicationResponse> getApplicationResponsesByCampaignId(int campaignId) {
        List<Application> applications = getApplicationsByCampaignId(campaignId);
        List<ApplicationResponse> applicationResponses = null;
        if (applications != null) {
            applicationResponses = new ArrayList<>();
            for (Application application : applications) {
                ApplicationResponse applicationResponse = new ApplicationResponse();
                applicationResponse.setApplicationId(application.getApplicationId());
                applicationResponse.setApplicationDate(application.getApplicationDate());
                applicationResponse.setStatus(application.getStatus());
                applicationResponse.setInternshipCampaignName(application.getInternshipCampaign().getCampaignName());
                applicationResponses.add(applicationResponse);
            }
        }
        return applicationResponses;
    }

    @Override
    public Application createApplication(CreateApplicationRequest createApplicationRequest) {
        Application application = new Application();
        application.setApplicationDate(createApplicationRequest.getApplicationDate());
        application.setStatus(createApplicationRequest.getStatus());
        application.setInternshipCampaign(internshipCampaignRepository.findById(createApplicationRequest.getInternshipCampaign()).orElse(null));
        return applicationRepository.save(application);
    }

    public Application updateApplicationStatus(int applicationId, String status) {
        Optional<Application> applicationOptional = applicationRepository.findById(applicationId);
        if (applicationOptional.isPresent()) {
            Application application = applicationOptional.get();
            application.setStatus(status);
            return applicationRepository.save(application);
        }
        return null;
    }
}
