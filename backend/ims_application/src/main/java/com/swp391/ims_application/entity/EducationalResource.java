package com.swp391.ims_application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Table(name = "educational_resources")
@Entity
public class EducationalResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_id")
    private int resourceId;

    @Column(name = "resource_name")
    private String resourceName;

    @Column(name = "description")
    private String description;

    @Column(name = "url")
    private String url;

    @Column(name = "created_date")
    private Date createdDate;

    @JsonProperty("isAvailable")
    @Column(name = "is_available")
    private boolean isAvailable;

    @OneToMany(mappedBy = "educationalResource")
    @JsonIgnore
    private List<ProgramTrainingResource> programTrainingResources;

}
