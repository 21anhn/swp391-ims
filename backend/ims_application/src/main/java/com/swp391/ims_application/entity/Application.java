package com.swp391.ims_application.entity;

import jakarta.persistence.*;

import java.util.Date;

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

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUserIntern() {
        return userIntern;
    }

    public void setUserIntern(User userIntern) {
        this.userIntern = userIntern;
    }

    public InternshipCampaign getInternshipCampaign() {
        return internshipCampaign;
    }

    public void setInternshipCampaign(InternshipCampaign internshipCampaign) {
        this.internshipCampaign = internshipCampaign;
    }
}
