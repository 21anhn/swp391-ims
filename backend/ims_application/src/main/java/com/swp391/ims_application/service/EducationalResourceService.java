package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.EducationalResource;
import com.swp391.ims_application.repository.EducationalResourceRepository;
import com.swp391.ims_application.service.imp.IEducationalResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EducationalResourceService implements IEducationalResourceService {

    private final EducationalResourceRepository repository;

    @Autowired
    public EducationalResourceService(EducationalResourceRepository repository) {
        this.repository = repository;
    }

    @Override
    public EducationalResource createEducationalResource(EducationalResource resource) {
        return repository.save(resource);
    }

    @Override
    @Transactional
    public EducationalResource updateEducationalResource(int resourceId, EducationalResource resource) {
        Optional<EducationalResource> existingResourceOptional = repository.findById(resourceId);
        if (existingResourceOptional.isPresent()) {
            EducationalResource existingResource = existingResourceOptional.get();
            existingResource.setResourceName(resource.getResourceName());
            existingResource.setDescription(resource.getDescription());
            existingResource.setCreatedDate(resource.getCreatedDate());
            existingResource.setAvailable(resource.isAvailable());

            return repository.save(existingResource);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void removeEducationalResource(int resourceId) {
        repository.deleteById(resourceId);
    }

    @Override
    public EducationalResource getEducationalResourceById(int resourceId) {
        return repository.findById(resourceId).orElse(null);
    }

    @Override
    public List<EducationalResource> getAllEducationalResources() {
        return repository.findAll();
    }
}
