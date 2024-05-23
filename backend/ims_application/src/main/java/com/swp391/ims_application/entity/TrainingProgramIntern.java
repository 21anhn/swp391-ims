package com.swp391.ims_application.entity;

import jakarta.persistence.*;

@Table(name = "training_program_intern")
@Entity
public class TrainingProgramIntern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id")
    private User userIntern;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private TrainingProgram trainingProgram;

    @Column(name = "feedback")
    private String feedback;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserIntern() {
        return userIntern;
    }

    public void setUserIntern(User userIntern) {
        this.userIntern = userIntern;
    }

    public TrainingProgram getTrainingProgram() {
        return trainingProgram;
    }

    public void setTrainingProgram(TrainingProgram trainingProgram) {
        this.trainingProgram = trainingProgram;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
