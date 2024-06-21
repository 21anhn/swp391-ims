package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.Skill;
import com.swp391.ims_application.payload.SkillDTO;
import com.swp391.ims_application.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public List<SkillDTO> getAllSkills() {
        List<Skill> skills = skillRepository.findAll();
        return mapToDTOList(skills);
    }

    public Optional<SkillDTO> getSkillById(int skillId) {
        Optional<Skill> skill = skillRepository.findById(skillId);
        return skill.map(this::mapToDTO);
    }

    public SkillDTO createSkill(SkillDTO skillDTO) {
        Skill skill = new Skill();
        skill.setSkillName(skillDTO.getSkillName());
        Skill createdSkill = skillRepository.save(skill);
        return mapToDTO(createdSkill);
    }

    public Optional<SkillDTO> updateSkill(int skillId, SkillDTO skillDTO) {
        Optional<Skill> optionalSkill = skillRepository.findById(skillId);
        if (optionalSkill.isPresent()) {
            Skill existingSkill = optionalSkill.get();
            existingSkill.setSkillName(skillDTO.getSkillName());
            Skill updatedSkill = skillRepository.save(existingSkill);
            return Optional.of(mapToDTO(updatedSkill));
        }
        return Optional.empty();
    }

    public boolean deleteSkill(int skillId) {
        Optional<Skill> optionalSkill = skillRepository.findById(skillId);
        if (optionalSkill.isPresent()) {
            skillRepository.delete(optionalSkill.get());
            return true;
        }
        return false;
    }

    private SkillDTO mapToDTO(Skill skill) {
        SkillDTO skillDTO = new SkillDTO();
        skillDTO.setSkillId(skill.getSkillId());
        skillDTO.setSkillName(skill.getSkillName());
        return skillDTO;
    }

    private List<SkillDTO> mapToDTOList(List<Skill> skills) {
        return skills.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}


