package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.Application;
import com.swp391.ims_application.entity.Schedule;
import com.swp391.ims_application.repository.ApplicationRepository;
import com.swp391.ims_application.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InterviewSchedulerService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    public void scheduleInterviews() {
        List<Application> approvedApplications = applicationRepository.findByStatus("approved");

        for (Application application : approvedApplications) {
            Schedule schedule = new Schedule();
            schedule.setInterviewDate(new Date()); // Set the interview date appropriately
            schedule.setInterviewLocation("Office"); // Set the interview location appropriately
            schedule.setStatus("scheduled");
            schedule.setUserCoordinator(application.getUserIntern());
            schedule.setInternshipCampaign(application.getInternshipCampaign());

            scheduleRepository.save(schedule);
        }
    }

    public List<Schedule> getAllInterviewSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getSchedulesByCampaignId(int campaignId) {
        return scheduleRepository.findByInternshipCampaignCampaignId(campaignId);
    }
}
