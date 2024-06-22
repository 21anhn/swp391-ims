package com.swp391.ims_application.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampaignReportDTO {
    private int campaignId;
    private String campaignName;
    private long numberOfInternsJoined;

    public CampaignReportDTO(int campaignId, String campaignName, long numberOfInternsJoined) {
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.numberOfInternsJoined = numberOfInternsJoined;
    }
}
