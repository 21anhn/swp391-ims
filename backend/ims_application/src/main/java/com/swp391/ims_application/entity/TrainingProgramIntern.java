package com.swp391.ims_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "training_program_interns")
@Entity
public class TrainingProgramIntern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tpi_id")
    private int tpiId;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private TrainingProgram trainingProgram;

    @ManyToOne
    @JoinColumn(name = "intern_id")
    private User userIntern;
}
