package com.ust.capstone.project_details_service.Repository;

import com.ust.capstone.project_details_service.entity.ProjectDetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDetailsRepository extends JpaRepository<ProjectDetails, Long> {
    List<ProjectDetails> findByUserId(Long userId);

    long countByIsApprovedTrue();

    long countByIsApprovedFalse();
}
