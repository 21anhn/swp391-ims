package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.entity.Application;
import com.swp391.ims_application.payload.CreateApplicationRequest;

import java.util.List;

public interface IApplicationService {
    List<Application> getAllApplications();
    Application createApplication(CreateApplicationRequest createApplicationRequest);

}
