package com.swp391.ims_application.controller;

import com.swp391.ims_application.entity.Schedule;
import com.swp391.ims_application.service.InterviewSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interviews")
public class InterviewController {

    @Autowired
    private InterviewSchedulerService interviewSchedulerService;

    @PostMapping("/schedule")
    public String scheduleInterviews() {
        interviewSchedulerService.scheduleInterviews();
        return "Interviews scheduled successfully.";
    }

    @GetMapping("/schedules")
    public List<Schedule> getAllInterviewSchedules() {
        return interviewSchedulerService.getAllInterviewSchedules();
    }

    @GetMapping("/schedules/campaign/{campaignId}")
    public List<Schedule> getSchedulesByCampaignId(@PathVariable int campaignId) {
        return interviewSchedulerService.getSchedulesByCampaignId(campaignId);
    }
}
