package com.swp391.ims_application.repository;

import com.swp391.ims_application.entity.TrainingProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingProgramRepository extends JpaRepository<TrainingProgram, Integer> {

    TrainingProgram findByProgramId(int id);

    List<TrainingProgram> findByUserMentor_UserId(int mentorId);

    long countByUserMentor_UserId(int mentorId);

    @Query("SELECT COUNT(tp) FROM TrainingProgram tp WHERE tp.isAvailable = true")
    long countByIsAvailableTrue();
}
