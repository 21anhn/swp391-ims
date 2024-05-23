package com.swp391.ims_application.entity;

import jakarta.persistence.*;

import java.util.Date;

@Table(name = "assessments")
@Entity
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assessment_id")
    private int assessmentId;

    @Column(name = "assessment_date")
    private Date assessmentDate;

    @Column(name = "score")
    private float score;

    @Column(name = "feedback")
    private String feedback;

    @ManyToOne
    @JoinColumn(name = "id")
    private User userIntern;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public Date getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(Date assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public User getUserIntern() {
        return userIntern;
    }

    public void setUserIntern(User userIntern) {
        this.userIntern = userIntern;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
