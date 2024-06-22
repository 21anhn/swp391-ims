    package com.swp391.ims_application.repository;

    import com.swp391.ims_application.entity.TrainingProgramIntern;
    import org.springframework.data.jpa.repository.JpaRepository;

    import java.util.List;

    public interface TrainingProgramInternRepository extends JpaRepository<TrainingProgramIntern, Integer> {
        List<TrainingProgramIntern> findAllByTrainingProgram_ProgramId(int programId);
    }

