package com.swp391.ims_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Table(name = "schedules")
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private int scheduleId;

    @Column(name = "interview_date")
    private Date interviewDate;

    @Column(name = "interview_location")
    private String interviewLocation;

    @Column(name = "status")
    private String status; //Lịch phỏng vấn còn khả dụng hay không

    @ManyToOne
    @JoinColumn(name = "id")
    private User userCoordinator;
}
