package com.swp391.ims_application.repository;

import com.swp391.ims_application.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByInternshipCampaignCampaignId(int campaignId);

    Application findByEmail(String email);

    List<Application> findByInternshipCampaignCampaignIdAndStatus(int campaignId, String status);

    long countByInternshipCampaignCampaignId(int campaignId);
}
