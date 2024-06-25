package com.swp391.ims_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
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
    private boolean status;

    @OneToMany(mappedBy = "schedule")
    private List<MentorSchedule> mentorSchedules;

    @OneToMany(mappedBy = "schedule")
    private List<ScheduleInternshipCampaign> scheduleInternshipCampaigns;
}
