package com.swp391.ims_application.repository;

import com.swp391.ims_application.entity.TrainingProgramIntern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingProgramInternRepository extends JpaRepository<TrainingProgramIntern, Integer> {
    @Query("SELECT COUNT(tpi.trainingProgram.programId) FROM TrainingProgramIntern tpi WHERE tpi.userIntern.userId = :internId")
    long countTrainingProgramsByInternId(@Param("internId") int internId);
    @Query("SELECT COUNT(DISTINCT tpi.userIntern) FROM TrainingProgramIntern tpi WHERE tpi.userIntern IS NOT NULL")
    long countDistinctByUserInternIsNotNull();
}
