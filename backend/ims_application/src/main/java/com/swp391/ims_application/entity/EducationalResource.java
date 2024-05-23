package com.swp391.ims_application.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Table(name = "educational_resources")
@Entity
public class EducationalResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resouce_id")
    private int resouceId;

    @Column(name = "resource_name")
    private String resourceName;

    @Column(name = "description")
    private String description;

    @Column(name = "created_date")
    private Date createdDate;

    @OneToMany(mappedBy = "educationalResource")
    private List<ProgramTrainingResource> programTrainingResourceList;

    public int getResouceId() {
        return resouceId;
    }

    public void setResouceId(int resouceId) {
        this.resouceId = resouceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<ProgramTrainingResource> getProgramTrainingResourceList() {
        return programTrainingResourceList;
    }

    public void setProgramTrainingResourceList(List<ProgramTrainingResource> programTrainingResourceList) {
        this.programTrainingResourceList = programTrainingResourceList;
    }
}
