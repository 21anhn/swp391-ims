package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.entity.Application;
import com.swp391.ims_application.payload.ApplicationResponse;
import com.swp391.ims_application.payload.CreateApplicationRequest;

import java.util.List;

public interface IApplicationService {
    List<Application> getAllApplications();

    List<Application> getApplicationsByCampaignId(int campaignId);

    List<ApplicationResponse> getApplicationResponsesByCampaignId(int campaignId);

    Application createApplication(CreateApplicationRequest createApplicationRequest);

    Application updateApplicationStatus(int applicationId, String status);

}
