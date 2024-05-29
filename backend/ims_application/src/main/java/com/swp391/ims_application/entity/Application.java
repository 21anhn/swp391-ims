package com.swp391.ims_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
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

    @ManyToOne
    @JoinColumn(name = "id")
    private User userIntern;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private InternshipCampaign internshipCampaign;

}
