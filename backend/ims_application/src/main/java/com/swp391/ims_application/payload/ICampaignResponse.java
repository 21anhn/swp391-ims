package com.swp391.ims_application.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ICampaignResponse {

    private int campaignId;
    private String campaignName;
    private String jobDescription;
    private String requirements;
    private Date postedDate;
    private Date deadline;
    private String hrName;

    public ICampaignResponse() {
    }

    public ICampaignResponse(int campaignId, String campaignName, String jobDescription, String requirements, Date postedDate, Date deadline, String hrName) {
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.jobDescription = jobDescription;
        this.requirements = requirements;
        this.postedDate = postedDate;
        this.deadline = deadline;
        this.hrName = hrName;
    }

}
