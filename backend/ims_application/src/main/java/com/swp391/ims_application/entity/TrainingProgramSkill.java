package com.swp391.ims_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "training_program_skills")
@Entity
public class TrainingProgramSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tps_id")
    private int tpsId;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private TrainingProgram trainingProgram;
}
