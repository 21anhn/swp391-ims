package com.swp391.ims_application.repository;

import com.swp391.ims_application.entity.TrainingProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingProgramRepository extends JpaRepository<TrainingProgram, Integer> {

    TrainingProgram findByProgramId(int id);

    List<TrainingProgram> findByUserMentorUserId(int userId);
}
