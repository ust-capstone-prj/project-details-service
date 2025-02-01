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
}
