package com.swp391.ims_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "program_training_resources")
@Entity
public class ProgramTrainingResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ptr_id")
    private int ptrId;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private EducationalResource educationalResource;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private TrainingProgram trainingProgram;
}
