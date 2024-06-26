package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.payload.InternDTO;
import com.swp391.ims_application.payload.InternProgressDTO;
import com.swp391.ims_application.payload.ScheduleDTO;
import com.swp391.ims_application.payload.UserDTO;

import java.util.List;


public interface IDashboardService {

    long countTrainingPrograms(int mentorId);

    List<InternDTO> getIntersByMentorId(int mentorId);

    List<InternProgressDTO> getInternProgress(int mentorId);

    List<ScheduleDTO> getUpcomingSchedules(int mentorId);
}
