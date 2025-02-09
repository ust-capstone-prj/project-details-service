package com.ust.capstone.project_details_service.Controller;

import com.ust.capstone.project_details_service.entity.ProjectDetails;
import com.ust.capstone.project_details_service.service.ProjectDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/project-details")
public class ProjectDetailsController {

    private final ProjectDetailsService projectDetailsService;

    @Autowired
    public ProjectDetailsController(ProjectDetailsService projectDetailsService) {
        this.projectDetailsService = projectDetailsService;
    }

    @GetMapping
    public List<ProjectDetails> getAllProjectDetails() {
        return projectDetailsService.getAllProjectDetails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDetails> getProjectDetailsById(@PathVariable Long id) {
        Optional<ProjectDetails> projectDetails = projectDetailsService.getProjectDetailsById(id);
        return projectDetails.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProjectDetails> createProjectDetails(@RequestBody ProjectDetails projectDetails) {
        ProjectDetails createdProjectDetails = projectDetailsService.createProjectDetails(projectDetails);
        return new ResponseEntity<>(createdProjectDetails, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDetails> updateProjectDetails(@PathVariable Long id,
            @RequestBody ProjectDetails projectDetails) {
        ProjectDetails updatedProjectDetails = projectDetailsService.updateProjectDetails(id, projectDetails);
        return updatedProjectDetails != null ? ResponseEntity.ok(updatedProjectDetails)
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/approval/{id}")
    public ResponseEntity<ProjectDetails> setProjectApproval(@PathVariable Long id) {
        return new ResponseEntity<ProjectDetails>(projectDetailsService.setApproval(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectDetails(@PathVariable Long id) {
        boolean isDeleted = projectDetailsService.deleteProjectDetails(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProjectDetails>> getProjectDetailsByUserId(@PathVariable Long userId) {
        List<ProjectDetails> projectDetails = projectDetailsService.getProjectDetailsByUserId(userId);
        return projectDetails.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(projectDetails);
    }
    @PutMapping("/soft-delete/{id}")
    public ResponseEntity<Void> softDeleteProjectDetails(@PathVariable Long id) {
        boolean isDeleted = projectDetailsService.softDeleteProjectDetails(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


//     @PutMapping("edit/{id}")
// public ResponseEntity<ProjectDetails> updateProjectPartial(@PathVariable Long id,
//                                                            @RequestParam(required = false) String phoneNumber,
//                                                            @RequestParam(required = false) Double sqftArea) {
//     ProjectDetails updatedProject = projectDetailsService.updateProjectPartial(id, phoneNumber, sqftArea);

//     System.out.println(updatedProject);
//     return updatedProject != null ? ResponseEntity.ok(updatedProject) : ResponseEntity.notFound().build();
// }

}
