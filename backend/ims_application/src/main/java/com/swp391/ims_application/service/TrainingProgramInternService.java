package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.TrainingProgram;
import com.swp391.ims_application.entity.TrainingProgramIntern;
import com.swp391.ims_application.entity.User;
import com.swp391.ims_application.repository.TrainingProgramInternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingProgramInternService {

    @Autowired
    private TrainingProgramInternRepository tpiRepository;

    @Transactional
    public TrainingProgramIntern addInternToProgram(TrainingProgram program, User intern) {
        TrainingProgramIntern tpi = new TrainingProgramIntern();
        tpi.setTrainingProgram(program);
        tpi.setUserIntern(intern);
        return tpiRepository.save(tpi);
    }

    @Transactional
    public boolean removeInternFromProgram(int tpiId) {
        Optional<TrainingProgramIntern> tpiOptional = tpiRepository.findById(tpiId);
        if (tpiOptional.isPresent()) {
            tpiRepository.delete(tpiOptional.get());
            return true;
        }
        return false;
    }

    public List<TrainingProgramIntern> getAllInternsByProgram(int programId) {
        return tpiRepository.findAllByTrainingProgram_ProgramId(programId);
    }
}