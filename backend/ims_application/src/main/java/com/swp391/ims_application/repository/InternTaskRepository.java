package com.swp391.ims_application.repository;

import com.swp391.ims_application.entity.InternTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternTaskRepository extends JpaRepository<InternTask, Integer> {
}
