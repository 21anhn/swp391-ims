package com.swp391.ims_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "training_program_intern")
@Entity
public class TrainingProgramIntern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id")
    private User userIntern;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private TrainingProgram trainingProgram;

    @Column(name = "feedback")
    private String feedback;

}
