package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.entity.EducationalResource;
import com.swp391.ims_application.payload.EducationalResourceDTO;

import java.util.List;

public interface IEducationalResourceService {

    boolean createEducationalResource(EducationalResourceDTO resourceDTO);

    boolean updateEducationalResource(EducationalResourceDTO resourceDTO);

    boolean removeEducationalResourceFromProgram(int resourceId, int programId);

    List<EducationalResourceDTO> getAllEducationalResources();

    EducationalResourceDTO getEducationalResourceById(int resourceId);

    List<EducationalResourceDTO> getEducationalResourcesByTrainingProgramAndIntern(int programId, int internId);
}