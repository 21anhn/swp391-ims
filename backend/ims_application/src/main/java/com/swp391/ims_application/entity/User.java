package com.swp391.ims_application.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Setter
@Getter
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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

    //MQH 1-N với Role
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "userHR")
    private List<InternshipCampaign> internshipCampaignList;

    @OneToMany(mappedBy = "userIntern")
    @JsonManagedReference
    private List<Application> applicationList;

    @OneToMany(mappedBy = "user")
    private List<EducationalBackground> educationalBackgroundList;

    @OneToMany(mappedBy = "user")
    private List<WorkHistory> workHistoryList;

    @OneToMany(mappedBy = "userCoordinator")
    private List<Schedule> scheduleList;

    @OneToMany(mappedBy = "userCoordinator")
    private List<TrainingProgram> trainingProgramList;

    @OneToMany(mappedBy = "userIntern")
    private List<TrainingProgramIntern> trainingProgramInternList;

    @OneToMany(mappedBy = "userMentor")
    private List<Task> taskList;

    @OneToMany(mappedBy = "userIntern")
    private List<Assessment> assessmentList_intern;

}
