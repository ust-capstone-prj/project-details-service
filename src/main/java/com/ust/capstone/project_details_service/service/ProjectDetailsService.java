package com.ust.capstone.project_details_service.service;

import com.ust.capstone.project_details_service.Repository.ProjectDetailsRepository;
import com.ust.capstone.project_details_service.entity.ProjectDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectDetailsService {

    private final ProjectDetailsRepository projectDetailsRepository;

    @Autowired
    public ProjectDetailsService(ProjectDetailsRepository projectDetailsRepository) {
        this.projectDetailsRepository = projectDetailsRepository;
    }

    public List<ProjectDetails> getAllProjectDetails() {
        return projectDetailsRepository.findAll();
    }

    public Optional<ProjectDetails> getProjectDetailsById(Long id) {
        return projectDetailsRepository.findById(id);
    }

    public ProjectDetails createProjectDetails(ProjectDetails projectDetails) {
        return projectDetailsRepository.save(projectDetails);
    }

    public ProjectDetails updateProjectDetails(Long id, ProjectDetails projectDetails) {
        if (projectDetailsRepository.existsById(id)) {
            projectDetails.setProjectId(id);
            return projectDetailsRepository.save(projectDetails);
        }
        return null;
    }

    public boolean deleteProjectDetails(Long id) {
        if (projectDetailsRepository.existsById(id)) {
            projectDetailsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ProjectDetails setApproval(Long id) {
        ProjectDetails project = projectDetailsRepository.findById(id).orElse(null);
        project.setApproved(true);
        return projectDetailsRepository.save(project);
    }

    public List<ProjectDetails> getProjectDetailsByUserId(Long userId) {
        return projectDetailsRepository.findByUserId(userId);
    }

    public boolean softDeleteProjectDetails(Long id) {
        Optional<ProjectDetails> projectDetails = projectDetailsRepository.findById(id);
        if (projectDetails.isPresent()) {
            ProjectDetails project = projectDetails.get();
            project.setDeleted(true);
            projectDetailsRepository.save(project);
            return true;
        }
        return false;
    }

    public ProjectDetails updateProjectPartial(Long id, String phoneNumber, Double sqftArea) {
        Optional<ProjectDetails> optionalProject = projectDetailsRepository.findById(id);
        if (optionalProject.isPresent()) {
            ProjectDetails project = optionalProject.get();
            // if (phoneNumber != null) {
            project.setPhoneNumber(phoneNumber);
            // }
            // if (sqftArea != null) {
            project.setSqftArea(sqftArea);
            // }
            System.out.println(project);
            return projectDetailsRepository.save(project);
        }

        return null;
    }

    public long getProjectCountByApprovalStatus(boolean isApproved) {
        if (isApproved) {
            return projectDetailsRepository.countByIsApprovedTrue();
        } else {
            return projectDetailsRepository.countByIsApprovedFalse();
        }
    }

}
