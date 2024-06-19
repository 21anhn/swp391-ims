package com.swp391.ims_application.controller;

import com.swp391.ims_application.payload.ScheduleDTO;
import com.swp391.ims_application.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/{campaignId}")
    public ResponseEntity<?> getSchedule(@PathVariable int campaignId) {
        List<ScheduleDTO> schedules = scheduleService.getSchedules(campaignId);
        if (schedules == null) {
            return new ResponseEntity<>("Not found any schedules in this internship campaign", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @PostMapping("/{campaignId}")
    public ResponseEntity<?> addSchedule(@PathVariable int campaignId, @RequestBody ScheduleDTO scheduleDTO) {
        boolean check = scheduleService.createSchedule(scheduleDTO, campaignId);
        if (check) {
            return new ResponseEntity<>("Successfully created schedule!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed create schedule!", HttpStatus.BAD_REQUEST);
    }
}
