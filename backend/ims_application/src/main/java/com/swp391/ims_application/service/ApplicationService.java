package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.Application;
import com.swp391.ims_application.entity.InternshipCampaign;
import com.swp391.ims_application.repository.ApplicationRepository;
import com.swp391.ims_application.payload.ApplicationDTO;
import com.swp391.ims_application.service.imp.IApplicationService;
import com.swp391.ims_application.service.imp.IFileService;
import com.swp391.ims_application.util.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.rmi.server.LogStream.log;

@Service
public class ApplicationService implements IApplicationService {

    private static final Logger log = LoggerFactory.getLogger(ApplicationService.class);
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private InternshipCampaignService intershipCampaignService;

    @Autowired
    private SendMailService sendMailService;

    @Autowired
    private IFileService fileService;

    @Override
    public boolean createApplication(ApplicationDTO applicationDTO, int campaignId, MultipartFile cvFile) throws IOException {
        InternshipCampaign i = intershipCampaignService.getById(campaignId);
        if (i == null) {
            return false;
        }
        String cvUrl = fileService.uploadFile(cvFile);
        Application application = new Application();
        application.setFullName(applicationDTO.getName());
        application.setEmail(applicationDTO.getEmail());
        application.setPhoneNumber(applicationDTO.getPhoneNumber());
        application.setApplicationDate(new Date());
        application.setInternshipCampaign(i);
        application.setStatus("Pending");
        application.setCvUrl(cvUrl);
        applicationRepository.save(application);
        return true;
    }

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
    public List<ApplicationDTO> getApplicationResponsesByCampaignId(int campaignId) {
        List<Application> applications = getApplicationsByCampaignId(campaignId);
        List<ApplicationDTO> applicationResponses = null;
        if (applications != null) {
            applicationResponses = new ArrayList<>();
            String[] arr;
            ApplicationDTO applicationDTO;
            for (Application application : applications) {
                applicationDTO = new ApplicationDTO();
                applicationDTO.setId(application.getApplicationId());
                applicationDTO.setName(application.getFullName());
                applicationDTO.setEmail(application.getEmail());
                applicationDTO.setPhoneNumber(application.getPhoneNumber());
                applicationDTO.setStatus(application.getStatus());
                if (application.getInterviewDate() != null) {
                    arr = Helper.splitDate(application.getInterviewDate(), "MM/dd/yyyy HH:mm");
                    applicationDTO.setDate(arr[0]);
                    applicationDTO.setTime(arr[1]);
                }
                if (application.getCvUrl() != null) {
                    applicationDTO.setCv(application.getCvUrl());
                }
                applicationResponses.add(applicationDTO);
            }
        }
        return applicationResponses;
    }

    @Override
    public boolean updateStatus(int applicationId, String status) {
        Application application = applicationRepository.findById(applicationId).get();
        if (application == null) {
            return false;
        }
        application.setStatus(status);
        if (status.equals("Approved")) {
            sendMailService.sendMail(application.getEmail(), "Interview date", "Click to this url to choose interview date: https://ims-system-e4f27.web.app/chooseSchedule");
        }
        applicationRepository.save(application);
        return true;
    }

    @Override
    public boolean updateInterviewDateInApplication(int applicationId, ApplicationDTO applicationDTO) {
        Application aInDb = applicationRepository.findById(applicationId).get();
        Application a = applicationRepository.findByEmail(applicationDTO.getEmail());
        if (a == null) {
            return false;
        }
        if (!a.getEmail().equals(aInDb.getEmail())) {
            return false;
        }
        try {
            Date d = Helper.parseStringToDate(applicationDTO.getDate(), applicationDTO.getTime(), "dd/MM/yyyy HH:mm");
            a.setInterviewDate(d);
            applicationRepository.save(a);
            return true;
        } catch (Exception e) {
            log("Error in updateInterviewDateInApplication: covert string to date!");
            return false;
        }
    }
}
