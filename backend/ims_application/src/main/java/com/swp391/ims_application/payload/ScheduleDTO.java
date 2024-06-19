package com.swp391.ims_application.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleDTO {
    private int scheduleId;
    private String date;
    private String time;
    private String location;

    public ScheduleDTO() {
    }

    public ScheduleDTO(int scheduleId, String date, String time, String location) {
        this.scheduleId = scheduleId;
        this.date = date;
        this.time = time;
        this.location = location;
    }
}
