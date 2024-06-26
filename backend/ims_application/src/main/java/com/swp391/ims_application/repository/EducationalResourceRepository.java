package com.swp391.ims_application.repository;


import com.swp391.ims_application.entity.EducationalResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationalResourceRepository extends JpaRepository<EducationalResource, Integer> {
    List<EducationalResource> findAll();

    EducationalResource findByResourceId(int id);
}

