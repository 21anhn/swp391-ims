package com.swp391.ims_application.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskDTO {
    private String taskName;
    private String taskDescription;
    private Date startTime;
    private Date endTime;
    private int programId;
}
