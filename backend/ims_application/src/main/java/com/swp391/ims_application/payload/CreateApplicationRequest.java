package com.swp391.ims_application.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateApplicationRequest {
    private Date applicationDate;
    private String status;
    private int userInternId;
    private int internshipCampaignId;

    public CreateApplicationRequest(Date applicationDate, String status, int userInternId, int internshipCampaignId) {
        this.applicationDate = applicationDate;
        this.status = status;
        this.userInternId = userInternId;
        this.internshipCampaignId = internshipCampaignId;
    }
}
