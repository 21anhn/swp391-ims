package com.swp391.ims_application.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InternDashboardDTO {
    private int totalTasks;
    private int completedTasks;
    private float completionRate;
}
