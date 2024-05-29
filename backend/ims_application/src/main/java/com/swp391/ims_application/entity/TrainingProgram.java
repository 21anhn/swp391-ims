package com.swp391.ims_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Table(name = "training_programs")
@Entity
public class TrainingProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private int programId;

    @Column(name = "program_name")
    private String programName;

    @Column(name = "description")
    private String description;

    @Column(name = "objectives")
    private String objectives;

    @ManyToOne
    @JoinColumn(name = "id")
    private User userCoordinator;

    @OneToMany(mappedBy = "trainingProgram")
    private List<TrainingProgramIntern> trainingProgramInternList;

    @OneToMany(mappedBy = "trainingProgram")
    private List<TrainingProgramSkill> trainingProgramSkillList;

    @OneToMany(mappedBy = "trainingProgram")
    private List<ProgramTrainingResource> programTrainingResourceList;

}
