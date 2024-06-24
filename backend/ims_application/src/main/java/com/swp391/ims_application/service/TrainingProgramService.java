package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.TrainingProgram;
import com.swp391.ims_application.entity.TrainingProgramIntern;
import com.swp391.ims_application.entity.User;
import com.swp391.ims_application.payload.AccountDTO;
import com.swp391.ims_application.payload.TrainingProgramDTO;
import com.swp391.ims_application.repository.TrainingProgramInternRepository;
import com.swp391.ims_application.repository.TrainingProgramRepository;
import com.swp391.ims_application.repository.UserRepository;
import com.swp391.ims_application.service.imp.ITrainingProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingProgramService implements ITrainingProgramService {

    @Autowired
    private TrainingProgramRepository trainingProgramRepository;

    @Autowired
    private UserRepository userRepository; // Để lấy thông tin User

    @Autowired
    private TrainingProgramInternRepository trainingProgramInternRepository;

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
            if (trainingProgram.getUserMentor() != null) {
                trainingProgramDTO.setMentorId(trainingProgram.getUserMentor().getUserId());
                trainingProgramDTO.setMentorName(trainingProgram.getUserMentor().getFullName());
            }
            trainingProgramDTOs.add(trainingProgramDTO);
        }
        return trainingProgramDTOs;
    }

    @Override
    public boolean editTrainingProgram(int programId, TrainingProgramDTO trainingProgramDTO) {
        if (trainingProgramDTO == null) return false;
        TrainingProgram trainingProgram = trainingProgramRepository.findByProgramId(programId);
        if (trainingProgramDTO.getProgramName() != null)
            trainingProgram.setProgramName(trainingProgramDTO.getProgramName());
        if (trainingProgramDTO.getDescription() != null)
            trainingProgram.setDescription(trainingProgramDTO.getDescription());
        if (trainingProgramDTO.getObjectives() != null)
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
    public boolean addInternToTrainingProgram(int programId, int internId) {
        Optional<TrainingProgram> optionalProgram = trainingProgramRepository.findById(programId);
        Optional<User> optionalIntern = userRepository.findById(internId);

        if (optionalProgram.isPresent() && optionalIntern.isPresent()) {
            TrainingProgram program = optionalProgram.get();
            User intern = optionalIntern.get();

            List<TrainingProgramIntern> interns = program.getTrainingProgramInterns();
            for (TrainingProgramIntern tpi : interns) {
                if (tpi.getUserIntern().getUserId() == internId) {
                    return false;
                }
            }

            TrainingProgramIntern newInternship = new TrainingProgramIntern();
            newInternship.setTrainingProgram(program);
            newInternship.setUserIntern(intern);
            trainingProgramInternRepository.save(newInternship);

            return true;
        }

        return false;
    }

    @Override
    public boolean removeInternFromTrainingProgram(int programId, int internId) {
        Optional<TrainingProgram> optionalProgram = trainingProgramRepository.findById(programId);
        Optional<User> optionalIntern = userRepository.findById(internId);

        if (optionalProgram.isPresent() && optionalIntern.isPresent()) {
            TrainingProgram program = optionalProgram.get();
            User intern = optionalIntern.get();

            List<TrainingProgramIntern> interns = program.getTrainingProgramInterns();
            for (TrainingProgramIntern tpi : new ArrayList<>(interns)) {
                if (tpi.getUserIntern().getUserId() == internId) {
                    trainingProgramInternRepository.delete(tpi);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public List<AccountDTO> getAllInternsInTrainingProgram(int programId) {
        Optional<TrainingProgram> optionalProgram = trainingProgramRepository.findById(programId);

        if (optionalProgram.isPresent()) {
            TrainingProgram program = optionalProgram.get();
            List<AccountDTO> internDTOs = new ArrayList<>();

            List<TrainingProgramIntern> interns = program.getTrainingProgramInterns();
            for (TrainingProgramIntern tpi : interns) {
                User intern = tpi.getUserIntern();
                AccountDTO accountDTO = new AccountDTO();
                accountDTO.setId(intern.getUserId());
                accountDTO.setUsername(intern.getUsername());
                accountDTO.setEmail(intern.getEmail());
                accountDTO.setPhoneNumber(intern.getPhoneNumber());
                accountDTO.setRoleName(intern.getRole().getRoleName());

                internDTOs.add(accountDTO);
            }

            return internDTOs;
        }

        return Collections.emptyList();//format line new
    }



}
