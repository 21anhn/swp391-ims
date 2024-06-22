package com.swp391.ims_application.repository;

import com.swp391.ims_application.entity.InternshipCampaign;
import com.swp391.ims_application.payload.CampaignReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipCampaignRepository extends JpaRepository<InternshipCampaign, Integer> {

    InternshipCampaign findByCampaignId(int id);

    List<InternshipCampaign> findByCampaignNameContaining(String name);

    @Query("SELECT new com.swp391.ims_application.payload.CampaignReportDTO(ic.campaignId, ic.campaignName, COUNT(a.applicationId)) " +
            "FROM InternshipCampaign ic " +
            "LEFT JOIN Application a ON ic.campaignId = a.internshipCampaign.campaignId " +
            "GROUP BY ic.campaignId, ic.campaignName")
    List<CampaignReportDTO> countInternsByCampaign();

}
