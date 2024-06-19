package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.InternshipCampaign;
import com.swp391.ims_application.entity.Schedule;
import com.swp391.ims_application.entity.ScheduleInternshipCampaign;
import com.swp391.ims_application.payload.ScheduleDTO;
import com.swp391.ims_application.repository.InternshipCampaignRepository;
import com.swp391.ims_application.repository.ScheduleICampaignRepository;
import com.swp391.ims_application.repository.ScheduleRepository;
import com.swp391.ims_application.service.imp.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        Date d;
        String dateString;
        String[] arr;
        ScheduleDTO scheduleDTO;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        for (Schedule schedule : schedules) {
            d = schedule.getInterviewDate();
            dateString = sdf.format(d);
            arr = dateString.split("\\s+");

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
        } catch (Exception e) {

        }
        schedule.setInterviewDate(d);
        schedule.setStatus("Available");
        schedule.setInterviewLocation(scheduleDTO.getLocation());
        scheduleRepository.save(schedule);

        ScheduleInternshipCampaign sic = new ScheduleInternshipCampaign();
        sic.setSchedule(schedule);
        sic.setInternshipCampaign(internshipCampaign);
        scheduleICampaignRepository.save(sic);
        return true;

    }
}
