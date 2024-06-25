package com.swp391.ims_application.repository;

import com.swp391.ims_application.entity.ProgramTrainingResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProgramTrainingResourceRepository extends JpaRepository<ProgramTrainingResource, Integer> {
    @Query("SELECT ptr FROM ProgramTrainingResource ptr WHERE ptr.educationalResource.resourceId = :resourceId AND ptr.trainingProgram.programId = :programId")
    ProgramTrainingResource findByResourceIdAndProgramId(@Param("resourceId") int resourceId, @Param("programId") int programId);

}