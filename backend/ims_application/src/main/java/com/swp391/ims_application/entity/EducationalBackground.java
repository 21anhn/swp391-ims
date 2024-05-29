package com.swp391.ims_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "educational_backgrounds")
@Entity
public class EducationalBackground {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "education_id")
    private int educationId;

    @Column(name = "university_name")
    private String universityName;

    @Column(name = "major")
    private String major;

    @Column(name = "degree")
    private String degree;

    @Column(name = "gpa")
    private float gpa;

    @Column(name = "graduation_year")
    private int graduationYear;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

}
