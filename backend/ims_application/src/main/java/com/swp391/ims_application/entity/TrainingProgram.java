package com.swp391.ims_application.entity;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "training_programs")
@Entity
public class TrainingProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private int programId;

    @Column(name = "program_name")
    private String programName;

    @Column(name = "description")
    private String description;

    @Column(name = "objectives")
    private String objectives;

    @ManyToOne
    @JoinColumn(name = "id")
    private User userCoordinator;

    @OneToMany(mappedBy = "trainingProgram")
    private List<TrainingProgramIntern> trainingProgramInternList;

    @OneToMany(mappedBy = "trainingProgram")
    private List<TrainingProgramSkill> trainingProgramSkillList;

    @OneToMany(mappedBy = "trainingProgram")
    private List<ProgramTrainingResource> programTrainingResourceList;

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public User getUserCoordinator() {
        return userCoordinator;
    }

    public void setUserCoordinator(User userCoordinator) {
        this.userCoordinator = userCoordinator;
    }

    public List<TrainingProgramIntern> getTrainingProgramInternList() {
        return trainingProgramInternList;
    }

    public void setTrainingProgramInternList(List<TrainingProgramIntern> trainingProgramInternList) {
        this.trainingProgramInternList = trainingProgramInternList;
    }

    public List<TrainingProgramSkill> getTrainingProgramSkillList() {
        return trainingProgramSkillList;
    }

    public void setTrainingProgramSkillList(List<TrainingProgramSkill> trainingProgramSkillList) {
        this.trainingProgramSkillList = trainingProgramSkillList;
    }

    public List<ProgramTrainingResource> getProgramTrainingResourceList() {
        return programTrainingResourceList;
    }

    public void setProgramTrainingResourceList(List<ProgramTrainingResource> programTrainingResourceList) {
        this.programTrainingResourceList = programTrainingResourceList;
    }
}
