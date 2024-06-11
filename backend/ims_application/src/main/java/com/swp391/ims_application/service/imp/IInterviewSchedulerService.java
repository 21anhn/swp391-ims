package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.entity.Schedule;

import java.util.Date;
import java.util.List;

public interface IInterviewSchedulerService {
    void scheduleInterviews();
    List<Schedule> getAllInterviewSchedules();
    List<Schedule> getSchedulesByCampaignId(int campaignId);
    boolean createInterviewSchedule(int applicationId, Date interviewDate, String location);
}
