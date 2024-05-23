package com.swp391.ims_application.entity;

import jakarta.persistence.*;

import java.util.Date;

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

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getInterviewLocation() {
        return interviewLocation;
    }

    public void setInterviewLocation(String interviewLocation) {
        this.interviewLocation = interviewLocation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUserCoordinator() {
        return userCoordinator;
    }

    public void setUserCoordinator(User userCoordinator) {
        this.userCoordinator = userCoordinator;
    }
}
