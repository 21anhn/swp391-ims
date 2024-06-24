package com.swp391.ims_application.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainingProgramDTO {
    private int programId;
    private String programName;
    private String description;
    private String objectives;
    private int mentorId;
    private String mentorName;
}
