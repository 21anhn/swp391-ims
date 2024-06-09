package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.entity.Application;
import com.swp391.ims_application.payload.ApplicationResponse;

import java.util.List;

public interface IApplicationService {
    List<Application> getAllApplications();

    List<Application> getApplicationsByCampaignId(int campaignId);

    List<ApplicationResponse> getApplicationResponsesByCampaignId(int campaignId);

}
