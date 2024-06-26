package com.swp391.ims_application.controller;

import com.swp391.ims_application.payload.InternDTO;
import com.swp391.ims_application.payload.InternProgressDTO;
import com.swp391.ims_application.payload.ScheduleDTO;
import com.swp391.ims_application.service.imp.IDashboardService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private IDashboardService dashboardService;

    @GetMapping("mentor/{mentorId}/count-training-programs")
    public long countTrainingPrograms(@PathVariable int mentorId) {
        return dashboardService.countTrainingPrograms(mentorId);
    }

    @GetMapping("mentor/{mentorId}/interns")
    public List<InternDTO> getIntersByMentorId(@PathVariable int mentorId) {
        return dashboardService.getIntersByMentorId(mentorId);
    }

    @GetMapping("mentor/{mentorId}/intern-progress")
    public List<InternProgressDTO> getInternProgress(@PathVariable int mentorId) {
        return dashboardService.getInternProgress(mentorId);
    }

    @GetMapping("mentor/{mentorId}/upcoming-schedules")
    public List<ScheduleDTO> getUpcomingSchedules(@PathVariable int mentorId) {
        return dashboardService.getUpcomingSchedules(mentorId);
    }
}
