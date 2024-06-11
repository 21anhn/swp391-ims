package com.swp391.ims_application.repository;

import com.swp391.ims_application.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByStatus(String status);
}
