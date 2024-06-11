package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.entity.Application;

import java.util.List;

public interface IApplicationService {
    List<Application> getAllApplications();
    boolean updateApplicationStatus(int id, String status);
}
