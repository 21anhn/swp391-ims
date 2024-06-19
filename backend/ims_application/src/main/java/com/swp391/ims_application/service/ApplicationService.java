package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.Application;
import com.swp391.ims_application.entity.InternshipCampaign;
import com.swp391.ims_application.payload.ApplicationDTO;
import com.swp391.ims_application.repository.ApplicationRepository;
import com.swp391.ims_application.service.imp.IApplicationService;
import com.swp391.ims_application.util.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
    private IntershipCampaignService intershipCampaignService;

    @Autowired
    private SendMailService sendMailService;

    @Override
    public boolean createApplication(ApplicationDTO applicationDTO, int campaignId) {
        InternshipCampaign i = intershipCampaignService.getById(campaignId);
        if (i == null) {
            return false;
        }
        Application application = new Application();
        application.setFullName(applicationDTO.getName());
        application.setEmail(applicationDTO.getEmail());
        application.setPhoneNumber(applicationDTO.getPhoneNumber());
        application.setApplicationDate(new Date());
        application.setInternshipCampaign(i);
        application.setStatus("Pending");
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
            for (Application application : applications) {
                ApplicationDTO applicationDTO = new ApplicationDTO();
                applicationDTO.setName(application.getFullName());
                applicationDTO.setEmail(application.getEmail());
                applicationDTO.setPhoneNumber(application.getPhoneNumber());
                applicationDTO.
                if(application.getInternshipCampaign() != null) {

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
    public boolean updateInterviewDateInApplication(ApplicationDTO applicationDTO) {
        Application a = applicationRepository.findByEmail(applicationDTO.getEmail());
        if (a == null) {
            return false;
        }
        try {
            Date d = Helper.parseStringToDate(applicationDTO.getDate(), applicationDTO.getTime(), "dd/MM/yyyy HH:mm");
            a.setInterviewDate(d);
            applicationRepository.save(a);
        } catch (Exception e) {
            log("Error in updateInterviewDateInApplication: covert string to date!");
            return false;
        }
        return true;
    }
}
