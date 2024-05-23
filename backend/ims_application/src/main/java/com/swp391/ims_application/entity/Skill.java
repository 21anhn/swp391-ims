package com.swp391.ims_application.entity;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "skills")
@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private int skillId;

    @Column(name = "skill_name")
    private String skillName;

    @OneToMany(mappedBy = "skill")
    private List<TrainingProgramSkill> trainingProgramSkillList;

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
