package com.swp391.ims_application.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainingProgramRequest {
    private String programName;
    private String description;
    private String objectives;
    private boolean isAvailable;
    private int coordinatorId;  // Assuming User IDs are integers
    private int mentorId;
}
