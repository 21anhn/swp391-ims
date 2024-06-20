package com.swp391.ims_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Table(name = "applications")
@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private int applicationId;

    @Column(name = "application_date")
    private Date applicationDate;

    @Column(name = "status")
    private String status;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "interview_date")
    private Date interviewDate;

    @Column(name = "cv_url")
    private String cvUrl;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private InternshipCampaign internshipCampaign;

    @OneToOne(mappedBy = "application")
    private User userIntern;
}
