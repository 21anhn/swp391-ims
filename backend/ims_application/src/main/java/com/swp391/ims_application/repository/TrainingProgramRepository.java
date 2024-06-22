package com.swp391.ims_application.repository;

import com.swp391.ims_application.entity.TrainingProgram;
import com.swp391.ims_application.payload.TrainingProgramDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainingProgramRepository extends JpaRepository<TrainingProgram, Integer> {

    TrainingProgram findByProgramId(int id);
    List<TrainingProgram> findByMentorId(int mentorId);

}
