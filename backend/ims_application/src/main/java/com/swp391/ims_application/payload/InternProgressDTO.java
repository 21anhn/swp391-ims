package com.swp391.ims_application.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InternProgressDTO {
    private String internName;
    private double progress;
    private String getProgramName;

    public InternProgressDTO(String internName, double progress, String getProgramName) {
        this.internName = internName;
        this.progress = progress;
        this.getProgramName = getProgramName;
    }
}