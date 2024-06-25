package com.swp391.ims_application.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeedbackDTO {
    private String comment;
    private float score;



    public FeedbackDTO(String comment, float score) {
        this.comment = comment;
        this.score = score;
    }
}
