package com.swp391.ims_application.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EducationalResourceDTO {
    private int resourceId;
    private String resourceName;
    private String description;
    private String url;
    private Date createdDate;
    private boolean isAvailable;
    private int trainingProgramId;

    public EducationalResourceDTO(int resourceId, String resourceName, String description, String url, Date createdDate, boolean isAvailable, int trainingProgramId) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.description = description;
        this.url = url;
        this.createdDate = createdDate;
        this.isAvailable = isAvailable;
        this.trainingProgramId = trainingProgramId;
    }
}
