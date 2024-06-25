package com.swp391.ims_application.repository;


import com.swp391.ims_application.entity.EducationalResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationalResourceRepository extends JpaRepository<EducationalResource, Integer> {
    List<EducationalResource> findAll();

    @Query("SELECT er FROM EducationalResource er JOIN ProgramTrainingResource ptr ON er.resourceId = ptr.educationalResource.resourceId JOIN TrainingProgram tp ON ptr.trainingProgram.programId = tp.programId WHERE tp.userMentor.userId = :mentorId")
    List<EducationalResource> findEducationalResourcesByMentorId(int mentorId);
}

