package com.swp391.ims_application.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationDTO {
    private String name;
    private String email;
    private String phoneNumber;
    private String date;
    private String time;
    private String cv;

    public ApplicationDTO() {
    }

    public ApplicationDTO(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
