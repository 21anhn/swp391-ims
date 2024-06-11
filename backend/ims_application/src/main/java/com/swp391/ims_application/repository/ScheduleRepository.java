package com.swp391.ims_application.repository;

import com.swp391.ims_application.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByInternshipCampaignCampaignId(int campaignId);
}
