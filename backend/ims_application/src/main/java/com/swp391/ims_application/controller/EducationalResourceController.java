package com.swp391.ims_application.controller;

import com.swp391.ims_application.entity.EducationalResource;
import com.swp391.ims_application.payload.EducationalResourceDTO;
import com.swp391.ims_application.service.EducationalResourceService;
import com.swp391.ims_application.service.imp.IEducationalResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/educational-resources")
public class EducationalResourceController {

    @Autowired
    private IEducationalResourceService educationalResourceService;

    @PostMapping()
    public ResponseEntity<?> createEducationalResource(@PathVariable int programId, @RequestBody EducationalResourceDTO resourceDTO) {
        resourceDTO.setTrainingProgramId(programId);
        resourceDTO.setAvailable(true);
        boolean created = educationalResourceService.createEducationalResource(resourceDTO);
        if (created) {
            return new ResponseEntity<>("Educational resource created successfully!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed to create educational resource!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{programId}")
    public ResponseEntity<?> editEducationalResource(@PathVariable int programId, @RequestBody EducationalResourceDTO resourceDTO) {
        resourceDTO.setTrainingProgramId(programId);
        boolean check = educationalResourceService.updateEducationalResource(resourceDTO);
        if (check) {
            return new ResponseEntity<>("Successfully edited educational resource!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to edit educational resource!", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<?> removeEducationalResource(@PathVariable int resourceId) {
        boolean removed = educationalResourceService.removeEducationalResource(resourceId);
        if (removed) {
            return new ResponseEntity<>("Educational resource removed successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to remove educational resource!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping()
    public ResponseEntity<?> getAllEducationalResources() {
        List<EducationalResourceDTO> resources = educationalResourceService.getAllEducationalResources();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<?> getEducationalResourceById(@PathVariable int resourceId) {
        EducationalResourceDTO resource = educationalResourceService.getEducationalResourceById(resourceId);
        if (resource != null) {
            return new ResponseEntity<>(resource, HttpStatus.OK);
        }
        return new ResponseEntity<>("Educational resource not found with id: " + resourceId, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/intern/{internId}/training-program/{programId}/educational-resources/")
    public ResponseEntity<List<EducationalResourceDTO>> getEducationalResourcesForIntern(@PathVariable int programId, @PathVariable int internId) {
        List<EducationalResourceDTO> resources = educationalResourceService.getEducationalResourcesByTrainingProgramAndIntern(programId, internId);
        if (resources.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resources);
    }
}
