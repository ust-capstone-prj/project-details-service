package com.ust.capstone.project_details_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "project_details")  
public class ProjectDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "project_id")  
    private Long projectId;

    @Column(name = "sqft_area")  
    private Double sqftArea;

    @Column(name = "project_variation_id")  
    private Long projectTypeCategoryVariationId;

    @Column(name = "contractor_id", nullable=true)  
    private Long contractorId;

    @Column(name = "user_id")  
    private Long userId;

}