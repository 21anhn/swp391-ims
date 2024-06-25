package com.swp391.ims_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "interns_tasks")
@Entity
public class InternTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "it_id")
    private int itId;

    @ManyToOne
    @JoinColumn(name = "intern_id")
    private User userIntern;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "score")
    private Float score;

    @Column(name = "comment")
    private String comment;

    @Column(name = "file_url")
    private String fileUrl;



    public boolean isCompleted() {
        return score != null;
    }
}
