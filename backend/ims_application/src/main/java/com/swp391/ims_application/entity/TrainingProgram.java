package com.swp391.ims_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
    @JoinColumn(name = "mentor_id")
    private User userMentor;

    @OneToMany(mappedBy = "trainingProgram")
    private List<TrainingProgramIntern> trainingProgramInterns;

    @OneToMany(mappedBy = "trainingProgram")
    private List<Task> tasks;

    @OneToMany(mappedBy = "trainingProgram")
    private List<TrainingProgramSkill> trainingProgramSkills;

    @OneToMany(mappedBy = "trainingProgram")
    private List<ProgramTrainingResource> programTrainingResources;
}
