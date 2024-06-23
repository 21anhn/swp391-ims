package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.entity.InternshipCampaign;

import java.util.List;

public interface IIternshipCampaignService {
    List<InternshipCampaign> getAll();

    InternshipCampaign getById(int id);

    List<InternshipCampaign> getByContainName(String name);

    boolean updateById(InternshipCampaign internshipCampaign);

    boolean deleteById(int id);


    long countApplicationsInCampaign(int campaignId);
}
