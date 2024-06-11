package com.swp391.ims_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduleId;

    private Date interviewDate;
    private String interviewLocation;
    private String status; // Lịch phỏng vấn còn khả dụng hay không

    @ManyToOne
    @JoinColumn(name = "coordinator_id")
    private User userCoordinator;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private InternshipCampaign internshipCampaign;
}
