package com.swp391.ims_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Setter
@Getter
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

}
