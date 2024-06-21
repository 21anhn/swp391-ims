package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.entity.TrainingProgram;
import com.swp391.ims_application.payload.TrainingProgramRequest;

public interface ITrainingProgramService {
    TrainingProgram createTrainingProgram(TrainingProgramRequest request);
    TrainingProgram getTrainingProgramById(int id);
    TrainingProgram updateTrainingProgram(int id, TrainingProgramRequest request);
    void deleteTrainingProgram(int id);
    void assignMentorToTrainingProgram(int programId, int mentorId);
}
