package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.payload.ScheduleDTO;

import java.util.List;

public interface IScheduleService {

    List<ScheduleDTO> getSchedules(int campaignId);

    boolean createSchedule(ScheduleDTO scheduleDTO, int campaignId);

}
