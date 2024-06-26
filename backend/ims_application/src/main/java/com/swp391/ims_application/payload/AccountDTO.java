package com.swp391.ims_application.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
    private int id;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private String roleName;
}
