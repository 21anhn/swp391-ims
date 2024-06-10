package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.Application;
import com.swp391.ims_application.payload.ApplicationResponse;
import com.swp391.ims_application.repository.ApplicationRepository;
import com.swp391.ims_application.service.imp.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationService implements IApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

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
}
