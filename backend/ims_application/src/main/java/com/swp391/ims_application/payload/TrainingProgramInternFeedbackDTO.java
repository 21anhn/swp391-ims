package com.swp391.ims_application.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TrainingProgramInternFeedbackDTO {
    private int internId;
    private int programId;
    private String feedback;
}
