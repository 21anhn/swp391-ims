package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.entity.Application;
import com.swp391.ims_application.payload.ApplicationDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IApplicationService {

    boolean createApplication(ApplicationDTO applicationDTO, int campaignId, MultipartFile cvFile) throws IOException;

    List<Application> getAllApplications();

    List<Application> getApplicationsByCampaignId(int campaignId);

    List<ApplicationDTO> getApplicationResponsesByCampaignId(int campaignId);

    boolean updateStatus(int applicationId, String status);

    boolean updateInterviewDateInApplication(int applicationId, ApplicationDTO applicationDTO);

    long countByCampaignId(int campaignId);
}
