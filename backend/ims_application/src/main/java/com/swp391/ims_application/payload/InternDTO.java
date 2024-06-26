package com.swp391.ims_application.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InternDTO {

    private int id;

    private String fullName;

    private String gender;

    private String email;

    private String phoneNumber;

    public InternDTO(int id, String fullName, String email, String phoneNumber, String gender) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }
}
