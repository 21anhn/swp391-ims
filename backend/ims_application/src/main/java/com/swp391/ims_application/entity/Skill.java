package com.swp391.ims_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
    private List<TrainingProgramSkill> trainingProgramSkills;
}
