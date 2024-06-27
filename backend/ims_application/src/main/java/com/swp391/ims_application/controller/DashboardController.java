package com.swp391.ims_application.controller;

import com.swp391.ims_application.payload.CustomResponse;
import com.swp391.ims_application.payload.InternDTO;
import com.swp391.ims_application.payload.InternProgressDTO;
import com.swp391.ims_application.payload.ScheduleDTO;
import com.swp391.ims_application.service.imp.IDashboardService;
import com.swp391.ims_application.service.imp.IIternshipCampaignService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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


    @GetMapping("/hr/{id}/applications/count")
    public long countApplicationsInCampaign(@PathVariable int id) {
        return dashboardService.countApplicationsInCampaign(id);
    }

    @GetMapping("/hr/internship-campaign/count")
    public ResponseEntity<?> countAvailableCampaigns() {
        long count = dashboardService.countAvailableCampaigns();
        CustomResponse customResponse = new CustomResponse();
        customResponse.setSuccess(true);
        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setMessage("Number of available internship campaigns");
        customResponse.setData(count);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    @GetMapping("/hr/user-intern/count")
    public ResponseEntity<Map<String, Object>> countAllInterns() {
        long count = dashboardService.countAllInterns();
        String message = "Number of internship users in the system";

        // Tạo một đối tượng Map để chứa dữ liệu phản hồi
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("count", count);

        // Trả về ResponseEntity chứa đối tượng Map
        return ResponseEntity.ok(response);
    }
}
