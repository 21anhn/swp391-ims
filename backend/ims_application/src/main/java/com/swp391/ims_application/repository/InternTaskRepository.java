package com.swp391.ims_application.repository;

import com.swp391.ims_application.entity.InternTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternTaskRepository extends JpaRepository<InternTask, Integer> {

    @Query("SELECT it FROM InternTask it WHERE it.task.trainingProgram.programId = :programId AND it.userIntern.userId = :internId")
    List<InternTask> findInternTasksByTrainingProgramAndIntern(@Param("programId") int programId, @Param("internId") int internId);

}
