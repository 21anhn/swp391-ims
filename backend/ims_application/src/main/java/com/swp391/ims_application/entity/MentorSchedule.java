package com.swp391.ims_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "mentors_schedules")
@Entity
public class MentorSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ms_id")
    private int msId;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private User userMentor;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
}
