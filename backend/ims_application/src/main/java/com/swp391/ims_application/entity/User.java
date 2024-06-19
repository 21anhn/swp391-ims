package com.swp391.ims_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "userMentor")
    private List<MentorSchedule> mentorSchedules;

    @OneToMany(mappedBy = "userHr")
    private List<InternshipCampaign> internshipCampaigns;

    @OneToOne(mappedBy = "userIntern")
    private Application application;

    @OneToMany(mappedBy = "userMentor")
    private List<TrainingProgram> trainingPrograms;

    @OneToMany(mappedBy = "userIntern")
    private List<TrainingProgramIntern> trainingProgramInterns;

    @OneToMany(mappedBy = "userIntern")
    private List<InternTask> internTasks;
}
