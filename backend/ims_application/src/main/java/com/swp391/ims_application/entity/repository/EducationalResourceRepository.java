package com.swp391.ims_application.entity.repository;


import com.swp391.ims_application.entity.EducationalResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationalResourceRepository extends JpaRepository<EducationalResource, Integer> {

}

