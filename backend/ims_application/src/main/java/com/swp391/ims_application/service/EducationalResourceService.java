package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.EducationalResource;
import com.swp391.ims_application.entity.ProgramTrainingResource;
import com.swp391.ims_application.entity.TrainingProgram;
import com.swp391.ims_application.payload.EducationalResourceDTO;
import com.swp391.ims_application.repository.EducationalResourceRepository;
import com.swp391.ims_application.repository.ProgramTrainingResourceRepository;
import com.swp391.ims_application.repository.TrainingProgramRepository;
import com.swp391.ims_application.service.imp.IEducationalResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationalResourceService implements IEducationalResourceService {

    @Autowired
    private EducationalResourceRepository educationalResourceRepository;

    @Autowired
    private TrainingProgramRepository trainingProgramRepository;

    private final ProgramTrainingResourceRepository programTrainingResourceRepository;

    @Autowired
    private ProgramTrainingResourceRepository programTrainingResourceRepository;


    @Override
    public boolean createEducationalResource(EducationalResourceDTO resourceDTO) {
        TrainingProgram trainingProgram = trainingProgramRepository.findById(resourceDTO.getTrainingProgramId()).orElse(null);
        if (trainingProgram == null) {
            return false;
        }
        EducationalResource resource = new EducationalResource();
        resource.setResourceName(resourceDTO.getResourceName());
        resource.setDescription(resourceDTO.getDescription());
        resource.setUrl(resourceDTO.getUrl());
        resource.setCreatedDate(new Date());
        resource.setAvailable(true);
        educationalResourceRepository.save(resource);

        ProgramTrainingResource ptr = new ProgramTrainingResource();
        ptr.setEducationalResource(resource);
        ptr.setTrainingProgram(trainingProgram);
        programTrainingResourceRepository.save(ptr);

        return true;
    }

    @Override
    public boolean updateEducationalResource(EducationalResourceDTO resourceDTO) {
        EducationalResource resource = educationalResourceRepository.findById(resourceDTO.getResourceId()).orElse(null);
        if (resource == null) {
            return false;
        }
        resource.setResourceName(resourceDTO.getResourceName());
        resource.setDescription(resourceDTO.getDescription());
        resource.setUrl(resourceDTO.getUrl());
        resource.setAvailable(true);
        educationalResourceRepository.save(resource);
        return true;
    }

    @Override
    public boolean removeEducationalResourceFromProgram(int resourceId, int programId) {
        ProgramTrainingResource ptr = programTrainingResourceRepository.findByResourceIdAndProgramId(resourceId, programId);
        if (ptr == null) {
            return false;
        }
        ptr.getEducationalResource().setAvailable(false);
        educationalResourceRepository.save(ptr.getEducationalResource());
        programTrainingResourceRepository.delete(ptr);
        return true;
    }

    @Override
    public List<EducationalResourceDTO> getAllEducationalResources() {
        List<EducationalResource> resources = educationalResourceRepository.findAll();
        return resources.stream().map(resource -> {
            int programId = 0;
            if (resource.getProgramTrainingResources() != null && !resource.getProgramTrainingResources().isEmpty()) {
                programId = resource.getProgramTrainingResources().get(0).getTrainingProgram().getProgramId();
            }
            return new EducationalResourceDTO(
                    resource.getResourceId(),
                    resource.getResourceName(),
                    resource.getDescription(),
                    resource.getUrl(),
                    resource.getCreatedDate(),
                    resource.isAvailable(),
                    programId
            );
        }).collect(Collectors.toList());
    }

    @Override
    public EducationalResourceDTO getEducationalResourceById(int resourceId) {
        EducationalResource resource = educationalResourceRepository.findById(resourceId).orElse(null);
        if (resource == null) {
            return null;
        }
        int programId = 0;
        if (resource.getProgramTrainingResources() != null && !resource.getProgramTrainingResources().isEmpty()) {
            programId = resource.getProgramTrainingResources().get(0).getTrainingProgram().getProgramId();
        }
        return new EducationalResourceDTO(
                resource.getResourceId(),
                resource.getResourceName(),
                resource.getDescription(),
                resource.getUrl(),
                resource.getCreatedDate(),
                resource.isAvailable(),
                programId
        );
    }

    @Override
    public List<EducationalResource> getEducationalResourcesByInternId(int internId) {
        return programTrainingResourceRepository.findAllEducationalResourcesByInternId(internId);
    }
}
