package com.swp391.ims_application.repository;

import com.swp391.ims_application.entity.Application;
import com.swp391.ims_application.payload.InternshipCampaignApplicationCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByInternshipCampaignCampaignId(int campaignId);

    Application findByEmail(String email);

    List<Application> findByInternshipCampaignCampaignIdAndStatus(int campaignId, String status);

    long countByInternshipCampaignCampaignId(int campaignId);

    @Query("SELECT COUNT(a) FROM Application a WHERE a.internshipCampaign.campaignId = :campaignId")
    long countApplicationsByCampaignId(@Param("campaignId") int campaignId);

    @Query("SELECT new com.swp391.ims_application.payload.InternshipCampaignApplicationCountDTO(ic.campaignId, COUNT(a)) " +
            "FROM InternshipCampaign ic LEFT JOIN ic.applications a " +
            "GROUP BY ic.campaignId")
    List<InternshipCampaignApplicationCountDTO> countApplicationsForEachCampaign();

}
