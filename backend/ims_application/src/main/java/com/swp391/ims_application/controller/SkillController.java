package com.swp391.ims_application.controller;

import com.swp391.ims_application.payload.SkillDTO;
import com.swp391.ims_application.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping
    public ResponseEntity<List<SkillDTO>> getAllSkills() {
        List<SkillDTO> skills = skillService.getAllSkills();
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/{skillId}")
    public ResponseEntity<SkillDTO> getSkillById(@PathVariable("skillId") int skillId) {
        Optional<SkillDTO> skill = skillService.getSkillById(skillId);
        return skill.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SkillDTO> createSkill(@RequestBody SkillDTO skillDTO) {
        SkillDTO createdSkill = skillService.createSkill(skillDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSkill);
    }

    @PutMapping("/{skillId}")
    public ResponseEntity<SkillDTO> updateSkill(@PathVariable("skillId") int skillId, @RequestBody SkillDTO skillDTO) {
        Optional<SkillDTO> updatedSkill = skillService.updateSkill(skillId, skillDTO);
        return updatedSkill.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<Void> deleteSkill(@PathVariable("skillId") int skillId) {
        boolean deleted = skillService.deleteSkill(skillId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

