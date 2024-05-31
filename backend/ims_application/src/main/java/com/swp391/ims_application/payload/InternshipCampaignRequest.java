package com.swp391.ims_application.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InternshipCampaignRequest {
    private int campaignId;
    private String campaignName;
    private String jobDescription;
    private String requirements;
    private Date postedDate;
    private Date deadline;
    private int hrId; // id của HR tạo post đó

    public InternshipCampaignRequest() {
    }

    public InternshipCampaignRequest(int campaignId, String campaignName, String jobDescription, String requirements, Date postedDate, Date deadline, int hrId) {
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.jobDescription = jobDescription;
        this.requirements = requirements;
        this.postedDate = postedDate;
        this.deadline = deadline;
        this.hrId = hrId;
    }
}
