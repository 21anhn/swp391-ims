package com.swp391.ims_application.repository;

import com.swp391.ims_application.entity.InternTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternTaskRepository extends JpaRepository<InternTask, Integer> {
    @Query("SELECT it.userIntern.userId, it.task.trainingProgram.programId, AVG(it.score) AS averageScore " +
            "FROM InternTask it " +
            "WHERE it.task.trainingProgram.programId = :programId " +
            "GROUP BY it.userIntern.userId, it.task.trainingProgram.programId")
    List<Object[]> calculateAverageScoreByProgramId(@Param("programId") int programId);
}
