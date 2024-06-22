package com.swp391.ims_application.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InternTaskDTO {
    private int itId;
    private int userInternId;
    private int taskId;
    private float score;
    private String comment;
    private String filePath;


    public InternTaskDTO() {}


    public InternTaskDTO(int itId, int userInternId, int taskId, float score, String comment, String filePath) {
        this.itId = itId;
        this.userInternId = userInternId;
        this.taskId = taskId;
        this.score = score;
        this.comment = comment;
        this.filePath = filePath;
    }
}
