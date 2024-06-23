package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.payload.AccountDTO;
import com.swp391.ims_application.payload.TrainingProgramDTO;

import java.util.List;

public interface ITrainingProgramService {

    boolean createTrainingProgram(TrainingProgramDTO trainingProgramDTO);

    TrainingProgramDTO getTrainingProgramById(int programId);

    List<TrainingProgramDTO> getAllTrainingPrograms();

    boolean editTrainingProgram(int programId, TrainingProgramDTO trainingProgramDTO);

    boolean deleteTrainingProgram(int programId);

    boolean addInternToTrainingProgram(int programId, int internId);

    boolean removeInternFromTrainingProgram(int programId, int internId);

    List<AccountDTO> getAllInternsInTrainingProgram(int programId); //format line
}
