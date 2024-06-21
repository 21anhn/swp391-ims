package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.entity.EducationalResource;

import java.util.List;

public interface IEducationalResourceService {
    EducationalResource createEducationalResource(EducationalResource resource);
    EducationalResource updateEducationalResource(int resourceId, EducationalResource resource);
    void removeEducationalResource(int resourceId);
    EducationalResource getEducationalResourceById(int resourceId);
    List<EducationalResource> getAllEducationalResources();
}