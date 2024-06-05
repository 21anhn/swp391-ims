package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.Application;
import com.swp391.ims_application.payload.CreateApplicationRequest;
import com.swp391.ims_application.repository.ApplicationRepository;
import com.swp391.ims_application.repository.InternshipCampaignRepository;
import com.swp391.ims_application.repository.UserRepository;
import com.swp391.ims_application.service.imp.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ApplicationServiceImpl implements IApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InternshipCampaignRepository internshipCampaignRepository;

    @Override
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }
    @Override
    public Application createApplication(CreateApplicationRequest createApplicationRequest) {
        Application application = new Application();
        application.setApplicationDate(createApplicationRequest.getApplicationDate());
        application.setStatus(createApplicationRequest.getStatus());
        application.setUserIntern(userRepository.findById(createApplicationRequest.getUserInternId()).orElse(null));
        application.setInternshipCampaign(internshipCampaignRepository.findById(createApplicationRequest.getInternshipCampaignId()).orElse(null));
        return applicationRepository.save(application);
    }
}
