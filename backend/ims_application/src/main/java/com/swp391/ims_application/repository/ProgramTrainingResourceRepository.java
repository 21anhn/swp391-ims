package com.swp391.ims_application.repository;

import com.swp391.ims_application.entity.EducationalResource;
import com.swp391.ims_application.entity.ProgramTrainingResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramTrainingResourceRepository extends JpaRepository<ProgramTrainingResource, Integer> {
    @Query("SELECT ptr FROM ProgramTrainingResource ptr WHERE ptr.educationalResource.resourceId = :resourceId AND ptr.trainingProgram.programId = :programId")
    ProgramTrainingResource findByResourceIdAndProgramId(@Param("resourceId") int resourceId, @Param("programId") int programId);

    @Query("SELECT ptr FROM ProgramTrainingResource ptr " +
            "JOIN ptr.trainingProgram tp " +
            "JOIN tp.trainingProgramInterns tpi " +
            "WHERE tp.programId = :programId " +
            "AND tpi.userIntern.userId = :internId")
    List<ProgramTrainingResource> findByProgramIdAndInternId(int programId, int internId);



}