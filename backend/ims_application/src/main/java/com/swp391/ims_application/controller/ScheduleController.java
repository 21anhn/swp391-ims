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
@CrossOrigin("*")
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

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<?> deleteSchedule(@PathVariable int scheduleId) {
        boolean check = scheduleService.deleteSchedule(scheduleId);
        if (check) {
            return new ResponseEntity<>("Successfully deleted schedule!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to delete schedule!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<?> editSchedule(@PathVariable int scheduleId, @RequestBody ScheduleDTO scheduleDTO) {
        boolean check = scheduleService.editSchedule(scheduleId, scheduleDTO);
        if (check) {
            return new ResponseEntity<>("Successfully edited schedule!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to edit schedule!", HttpStatus.BAD_REQUEST);
    }


}
