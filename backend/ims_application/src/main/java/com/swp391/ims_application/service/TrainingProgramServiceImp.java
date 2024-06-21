package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.TrainingProgram;
import com.swp391.ims_application.entity.User;
import com.swp391.ims_application.payload.TrainingProgramRequest;
import com.swp391.ims_application.repository.TrainingProgramRepository;
import com.swp391.ims_application.repository.UserRepository;
import com.swp391.ims_application.service.imp.ITrainingProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainingProgramServiceImp implements ITrainingProgramService {
    @Autowired
    private TrainingProgramRepository trainingProgramRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TrainingProgram createTrainingProgram(TrainingProgramRequest request) {
        TrainingProgram program = new TrainingProgram();
        program.setProgramName(request.getProgramName());
        program.setDescription(request.getDescription());
        program.setObjectives(request.getObjectives());
        program.setAvailable(request.isAvailable());

        User coordinator = userRepository.findById(request.getCoordinatorId()).orElse(null);
        User mentor = userRepository.findById(request.getMentorId()).orElse(null);
        program.setUserCoordinator(coordinator);
        program.setUserMentor(mentor);

        return trainingProgramRepository.save(program);
    }

    @Override
    public TrainingProgram getTrainingProgramById(int id) {
        return trainingProgramRepository.findById(id).orElse(null);
    }

    @Override
    public TrainingProgram updateTrainingProgram(int id, TrainingProgramRequest request) {
        Optional<TrainingProgram> optionalProgram = trainingProgramRepository.findById(id);
        if (optionalProgram.isPresent()) {
            TrainingProgram program = optionalProgram.get();
            program.setProgramName(request.getProgramName());
            program.setDescription(request.getDescription());
            program.setObjectives(request.getObjectives());
            program.setAvailable(request.isAvailable());

            User coordinator = userRepository.findById(request.getCoordinatorId()).orElse(null);
            User mentor = userRepository.findById(request.getMentorId()).orElse(null);
            program.setUserCoordinator(coordinator);
            program.setUserMentor(mentor);

            return trainingProgramRepository.save(program);
        }
        return null;
    }

    @Override
    public void deleteTrainingProgram(int id) {
        trainingProgramRepository.deleteById(id);
    }

    @Override
    public void assignMentorToTrainingProgram(int programId, int mentorId) {
        Optional<TrainingProgram> optionalProgram = trainingProgramRepository.findById(programId);
        Optional<User> optionalMentor = userRepository.findById(mentorId);
        if (optionalProgram.isPresent() && optionalMentor.isPresent()) {
            TrainingProgram program = optionalProgram.get();
            program.setUserMentor(optionalMentor.get());
            trainingProgramRepository.save(program);
        }
    }

}
