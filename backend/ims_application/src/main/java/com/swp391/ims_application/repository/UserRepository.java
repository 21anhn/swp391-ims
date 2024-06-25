package com.swp391.ims_application.repository;

import com.swp391.ims_application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.application.applicationId = :applicationId")
    User findByApplicationApplicationId(@Param("applicationId") int applicationApplicationId);

    List<User> findByRoleRoleName(String roleName);

    long count();

    long countByRoleRoleName(String roleName);

}
