package com.swp391.ims_application.repository;

import com.swp391.ims_application.entity.Schedule;
import com.swp391.ims_application.entity.ScheduleInternshipCampaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleICampaignRepository extends JpaRepository<ScheduleInternshipCampaign, Integer> {

    @Query("SELECT s FROM Schedule s JOIN s.scheduleInternshipCampaigns sic WHERE sic.internshipCampaign.campaignId = :campaignId")
    List<Schedule> findSchedulesByCampaignId(@Param("campaignId") int campaignId);
}
