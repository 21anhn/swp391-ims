package com.swp391.ims_application.entity;

import jakarta.persistence.*;

@Table(name = "training_program_skill")
@Entity
public class TrainingProgramSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private TrainingProgram trainingProgram;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public TrainingProgram getTrainingProgram() {
        return trainingProgram;
    }

    public void setTrainingProgram(TrainingProgram trainingProgram) {
        this.trainingProgram = trainingProgram;
    }
}
