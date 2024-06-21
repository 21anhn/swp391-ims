package com.swp391.ims_application.repository;

import com.swp391.ims_application.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
