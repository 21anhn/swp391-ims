package com.swp391.ims_application.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Table(name = "internship_campaigns")
@Entity
public class InternshipCampaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id")
    private int campaignId;

    @Column(name = "campaign_name")
    private String campaignName;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "requirements")
    private String requirements;

    @Column(name = "posted_date")
    private Date postedDate;

    @Column(name = "deadline")
    private Date deadline;

    @ManyToOne
    @JoinColumn(name = "hr_id")
    private User userHR;

    @OneToMany(mappedBy = "internshipCampaign")
    private List<Application> applications;

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public User getUserHR() {
        return userHR;
    }

    public void setUserHR(User userHR) {
        this.userHR = userHR;
    }
}
