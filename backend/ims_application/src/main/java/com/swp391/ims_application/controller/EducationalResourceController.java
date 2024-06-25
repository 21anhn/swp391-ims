package com.swp391.ims_application.controller;

import com.swp391.ims_application.entity.EducationalResource;
import com.swp391.ims_application.service.EducationalResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/educational-resources")
public class EducationalResourceController {

    private final EducationalResourceService service;

    @Autowired
    public EducationalResourceController(EducationalResourceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EducationalResource> createEducationalResource(@RequestBody EducationalResource resource) {
        return ResponseEntity.ok(service.createEducationalResource(resource));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EducationalResource> updateEducationalResource(
            @PathVariable int id, @RequestBody EducationalResource resource) {
        EducationalResource updatedResource = service.updateEducationalResource(id, resource);
        if (updatedResource != null) {
            return ResponseEntity.ok(updatedResource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeEducationalResource(@PathVariable int id) {
        service.removeEducationalResource(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EducationalResource>> getAllEducationalResources() {
        return ResponseEntity.ok(service.getAllEducationalResources());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationalResource> getEducationalResourceById(@PathVariable int id) {
        EducationalResource resource = service.getEducationalResourceById(id);
        if (resource != null) {
            return ResponseEntity.ok(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/intern/{internId}")
    public ResponseEntity<List<EducationalResource>> getEducationalResourcesByInternId(@PathVariable int internId) {
        List<EducationalResource> resources = service.getEducationalResourcesByInternId(internId);
        if (resources.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }
}
