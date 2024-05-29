package com.swp391.ims_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "program_training_resources")
@Entity
@Getter
@Setter
public class ProgramTrainingResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "resouce_id")
    private EducationalResource educationalResource;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private TrainingProgram trainingProgram;
}
