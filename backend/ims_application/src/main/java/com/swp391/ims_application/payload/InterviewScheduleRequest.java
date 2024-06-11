package com.swp391.ims_application.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InterviewScheduleRequest {
    private int applicationId;
    private Date interviewDate;
    private String location;
}
