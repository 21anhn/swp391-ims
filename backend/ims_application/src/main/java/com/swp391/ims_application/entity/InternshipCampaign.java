package com.swp391.ims_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Table(name = "intership_campaigns")
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

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "hr_id")
    private User userHr;

    @OneToMany(mappedBy = "internshipCampaign")
    private List<ScheduleInternshipCampaign> scheduleInternshipCampaigns;

    @OneToMany(mappedBy = "internshipCampaign")
    private List<Application> applications;
}
