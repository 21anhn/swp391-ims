package com.swp391.ims_application.payload;

import com.swp391.ims_application.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountRequest {
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private Role role;
}
