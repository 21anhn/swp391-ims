package com.swp391.ims_application.repository;

import com.swp391.ims_application.entity.TrainingProgramIntern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingProgramInternRepository extends JpaRepository<TrainingProgramIntern, Integer> {

    @Modifying
    @Query("DELETE FROM TrainingProgramIntern tpi WHERE tpi.trainingProgram.programId = :programId AND tpi.userIntern.userId = :internId")
    void deleteByProgramIdAndInternId(@Param("programId") int programId, @Param("internId") int internId);

}
