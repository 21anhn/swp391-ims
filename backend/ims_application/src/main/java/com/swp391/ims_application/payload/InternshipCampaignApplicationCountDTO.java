package com.swp391.ims_application.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InternshipCampaignApplicationCountDTO {
    private int campaignId;
    private long applicationCount;
}
