package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.Application;
import com.swp391.ims_application.repository.ApplicationRepository;
import com.swp391.ims_application.service.imp.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements IApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }
}
