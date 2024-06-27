package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.*;
import com.swp391.ims_application.payload.InternDTO;
import com.swp391.ims_application.payload.InternProgressDTO;
import com.swp391.ims_application.payload.ScheduleDTO;
import com.swp391.ims_application.payload.UserDTO;
import com.swp391.ims_application.repository.*;
import com.swp391.ims_application.service.imp.IDashboardService;
import com.swp391.ims_application.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DashboardService implements IDashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainingProgramRepository trainingProgramRepository;

    @Autowired
    private InternTaskRepository internTaskRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    InternshipCampaignRepository internshipCampaignRepository;

    @Override
    public long countTrainingPrograms(int mentorId) {
        return trainingProgramRepository.countByUserMentor_UserId(mentorId);
    }

    @Override
    public List<InternDTO> getIntersByMentorId(int mentorId) {
        List<InternDTO> internsDTO = new ArrayList<>();
        Set<Integer> processedInternIds = new HashSet<>(); // Use a set to track processed intern IDs

        List<TrainingProgram> trainingPrograms = trainingProgramRepository.findByUserMentor_UserId(mentorId);

        for (TrainingProgram program : trainingPrograms) {
            List<TrainingProgramIntern> programInterns = program.getTrainingProgramInterns();
            for (TrainingProgramIntern programIntern : programInterns) {
                User userIntern = programIntern.getUserIntern();
                int internId = userIntern.getUserId();

                // Check if intern ID is already processed to avoid duplicates
                if (!processedInternIds.contains(internId)) {
                    internsDTO.add(new InternDTO(internId, userIntern.getFullName(),
                            userIntern.getEmail(), userIntern.getPhoneNumber(), userIntern.getGender()));
                    processedInternIds.add(internId); // Add intern ID to processed set
                }
            }
        }

        return internsDTO;
    }

    @Override
    public List<InternProgressDTO> getInternProgress(int mentorId) {
        List<InternProgressDTO> progressDTOList = new ArrayList<>();
        List<TrainingProgram> trainingPrograms = trainingProgramRepository.findByUserMentor_UserId(mentorId);

        for (TrainingProgram program : trainingPrograms) {
            Map<Integer, Double> internProgressMap = new HashMap<>();

            List<TrainingProgramIntern> programInterns = program.getTrainingProgramInterns();

            for (TrainingProgramIntern programIntern : programInterns) {
                User userIntern = programIntern.getUserIntern();
                double progress = internProgressMap.getOrDefault(userIntern.getUserId(), 0.0);

                long totalTasks = program.getTasks().size();
                long completedTasks = internTaskRepository.countByUserIntern_UserIdAndTask_TrainingProgram(userIntern.getUserId(), program);

                double internProgress = (totalTasks > 0) ? ((double) completedTasks / totalTasks) * 100 : 0.0;
                progress += internProgress;

                internProgressMap.put(userIntern.getUserId(), progress);
            }

            for (Map.Entry<Integer, Double> entry : internProgressMap.entrySet()) {
                User userIntern = userRepository.findById(entry.getKey()).orElse(null);
                if (userIntern != null) {
                    progressDTOList.add(new InternProgressDTO(userIntern.getFullName(), entry.getValue(), program.getProgramName()));
                }
            }
        }

        return progressDTOList;
    }


    @Override
    public List<ScheduleDTO> getUpcomingSchedules(int mentorId) {
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        List<MentorSchedule> mentorSchedules = userRepository.findById(mentorId).orElseThrow().getMentorSchedules();

        String[] arr;
        for (MentorSchedule mentorSchedule : mentorSchedules) {
            Schedule schedule = mentorSchedule.getSchedule();
            arr = Helper.splitDate(schedule.getInterviewDate(), "MM/dd/yyyy HH:mm");
            scheduleDTOList.add(new ScheduleDTO(schedule.getScheduleId(), arr[0], arr[1], schedule.getInterviewLocation()));
        }

        return scheduleDTOList;
    }

    @Override
    public long countApplicationsInCampaign(int campaignId) {
        return applicationRepository.countByInternshipCampaignCampaignId(campaignId);
    }

    @Override
    public long countAvailableCampaigns() {
        return internshipCampaignRepository.countByIsAvailableTrue();
    }

    @Override
    public long countAllInterns() {
        return userRepository.countInterns();
    }

    @Override
    public long countAvailableTrainingPrograms() {
        return trainingProgramRepository.countByIsAvailableTrue();
    }
}
