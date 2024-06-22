package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.entity.TrainingProgram;
import com.swp391.ims_application.payload.TrainingProgramDTO;

import java.util.List;

public interface ITrainingProgramService {

    public boolean createTrainingProgram(TrainingProgramDTO trainingProgramDTO);

    public TrainingProgramDTO getTrainingProgramById(int programId);

    public List<TrainingProgramDTO> getAllTrainingPrograms();

    public boolean editTrainingProgram(int programId, TrainingProgramDTO trainingProgramDTO);

    public boolean deleteTrainingProgram(int programId);
    List<TrainingProgram> getTrainingProgramsManagedByMentor(int mentorId);


}
