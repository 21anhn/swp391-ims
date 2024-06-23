package com.swp391.ims_application.repository;

import com.swp391.ims_application.entity.InternshipCampaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InternshipCampaignRepository extends JpaRepository<InternshipCampaign, Integer> {

    InternshipCampaign findByCampaignId(int id);

    List<InternshipCampaign> findByCampaignNameContaining(String name);

    long count();
}
