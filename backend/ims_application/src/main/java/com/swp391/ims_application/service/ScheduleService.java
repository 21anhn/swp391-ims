package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.InternshipCampaign;
import com.swp391.ims_application.entity.Schedule;
import com.swp391.ims_application.entity.ScheduleInternshipCampaign;
import com.swp391.ims_application.payload.ScheduleDTO;
import com.swp391.ims_application.repository.InternshipCampaignRepository;
import com.swp391.ims_application.repository.ScheduleICampaignRepository;
import com.swp391.ims_application.repository.ScheduleRepository;
import com.swp391.ims_application.service.imp.IScheduleService;
import com.swp391.ims_application.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleService implements IScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private InternshipCampaignRepository internshipCampaignRepository;

    @Autowired
    private ScheduleICampaignRepository scheduleICampaignRepository;

    @Override
    public List<ScheduleDTO> getSchedules(int campaignId) {
        List<ScheduleDTO> res = new ArrayList<>();
        List<Schedule> schedules = scheduleICampaignRepository.findSchedulesByCampaignId(campaignId);

        if (schedules == null || schedules.size() == 0) {
            return null;
        }

        String[] arr;
        ScheduleDTO scheduleDTO;

        for (Schedule schedule : schedules) {
            arr = Helper.splitDate(schedule.getInterviewDate(), "MM/dd/yyyy HH:mm");

            scheduleDTO = new ScheduleDTO(schedule.getScheduleId(), arr[0], arr[1], schedule.getInterviewLocation());
            res.add(scheduleDTO);
        }
        return res;
    }

    @Override
    public boolean createSchedule(ScheduleDTO scheduleDTO, int campaignId) {
        InternshipCampaign internshipCampaign = internshipCampaignRepository.findByCampaignId(campaignId);
        if (internshipCampaign == null) {
            return false;
        }
        Schedule schedule = new Schedule();
        Date d = null;
        try {
            String dateTimeString = scheduleDTO.getDate() + " " + scheduleDTO.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            d = sdf.parse(dateTimeString);
            schedule.setInterviewDate(d);
            schedule.setStatus(true);
            schedule.setInterviewLocation(scheduleDTO.getLocation());
            scheduleRepository.save(schedule);

            ScheduleInternshipCampaign sic = new ScheduleInternshipCampaign();
            sic.setSchedule(schedule);
            sic.setInternshipCampaign(internshipCampaign);
            scheduleICampaignRepository.save(sic);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteSchedule(int scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElse(null);
        if (schedule == null) {
            return false;
        }
        schedule.setStatus(false);
        scheduleRepository.save(schedule);
        return true;
    }

    @Override
    public boolean editSchedule(int scheduleId, ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElse(null);
        if (schedule == null) {
            return false;
        }

        if (scheduleDTO.getDate() != null && scheduleDTO.getTime() != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                Date interviewDateTime = sdf.parse(scheduleDTO.getDate() + " " + scheduleDTO.getTime());
                schedule.setInterviewDate(interviewDateTime);
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }
        }

        if (scheduleDTO.getLocation() != null) {
            schedule.setInterviewLocation(scheduleDTO.getLocation());
        }

        schedule.setStatus(true);

        scheduleRepository.save(schedule);
        return true;
    }

}
