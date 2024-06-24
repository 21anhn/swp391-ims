package com.swp391.ims_application.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportByAverageScoreDTO {
    private int internId;
    private int programId;
    private float averageScore;

    public ReportByAverageScoreDTO(int internId, int programId, float averageScore) {
        this.internId = internId;
        this.programId = programId;
        this.averageScore = averageScore;
    }
}
