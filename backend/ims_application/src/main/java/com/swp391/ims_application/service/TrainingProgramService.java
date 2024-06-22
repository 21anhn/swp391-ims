package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.TrainingProgram;
import com.swp391.ims_application.payload.TrainingProgramDTO;
import com.swp391.ims_application.repository.TrainingProgramRepository;
import com.swp391.ims_application.service.imp.ITrainingProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingProgramService implements ITrainingProgramService {

    @Autowired
    private TrainingProgramRepository trainingProgramRepository;

    @Override
    public boolean createTrainingProgram(TrainingProgramDTO trainingProgramDTO) {
        if (trainingProgramDTO == null) return false;
        TrainingProgram trainingProgram = new TrainingProgram();
        trainingProgram.setProgramName(trainingProgramDTO.getProgramName());
        trainingProgram.setDescription(trainingProgramDTO.getDescription());
        trainingProgram.setObjectives(trainingProgramDTO.getObjectives());
        trainingProgram.setAvailable(true);
        trainingProgramRepository.save(trainingProgram);
        return true;
    }

    @Override
    public TrainingProgramDTO getTrainingProgramById(int programId) {
        TrainingProgram trainingProgram = trainingProgramRepository.findById(programId).get();
        TrainingProgramDTO trainingProgramDTO = new TrainingProgramDTO();
        trainingProgramDTO.setProgramId(trainingProgram.getProgramId());
        trainingProgramDTO.setDescription(trainingProgram.getDescription());
        trainingProgramDTO.setObjectives(trainingProgram.getObjectives());
        trainingProgramDTO.setProgramName(trainingProgram.getProgramName());
        return trainingProgramDTO;
    }

    @Override
    public List<TrainingProgramDTO> getAllTrainingPrograms() {
        List<TrainingProgram> trainingPrograms = trainingProgramRepository.findAll();
        List<TrainingProgramDTO> trainingProgramDTOs = new ArrayList<>();
        for (TrainingProgram trainingProgram : trainingPrograms) {
            TrainingProgramDTO trainingProgramDTO = new TrainingProgramDTO();
            trainingProgramDTO.setProgramId(trainingProgram.getProgramId());
            trainingProgramDTO.setProgramName(trainingProgram.getProgramName());
            trainingProgramDTO.setDescription(trainingProgram.getDescription());
            trainingProgramDTO.setObjectives(trainingProgram.getObjectives());
            trainingProgramDTOs.add(trainingProgramDTO);
        }
        return trainingProgramDTOs;
    }

    @Override
    public boolean editTrainingProgram(int programId, TrainingProgramDTO trainingProgramDTO) {
        if (trainingProgramDTO == null) return false;
        TrainingProgram trainingProgram = trainingProgramRepository.findByProgramId(programId);
        if(trainingProgramDTO.getProgramName() != null)
            trainingProgram.setProgramName(trainingProgramDTO.getProgramName());
        if(trainingProgramDTO.getDescription() != null)
            trainingProgram.setDescription(trainingProgramDTO.getDescription());
        if(trainingProgramDTO.getObjectives() != null)
            trainingProgram.setObjectives(trainingProgramDTO.getObjectives());
        trainingProgramRepository.save(trainingProgram);
        return true;
    }

    @Override
    public boolean deleteTrainingProgram(int id) {
        TrainingProgram trainingProgram = trainingProgramRepository.findById(id).get();
        trainingProgram.setAvailable(false);
        trainingProgramRepository.save(trainingProgram);
        return true;
    }

    @Override
    public List<TrainingProgram> getTrainingProgramsManagedByMentor(int mentorId) {
        return trainingProgramRepository.findByMentorId(mentorId);
    }
}
