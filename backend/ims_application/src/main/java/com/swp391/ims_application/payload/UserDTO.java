package com.swp391.ims_application.payload;

import com.swp391.ims_application.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class UserDTO {

    private int id;

    private String username;

    private String password;

    private String fullName;

    private Date dob;

    private String gender;

    private String email;

    private String phoneNumber;

    private String address;

    private int applicationId;

    private String roleName;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String fullName, Date dob, String gender, String email, String phoneNumber, String address, String roleName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.roleName = roleName;
    }

    public UserDTO(User user) {
        this.id = user.getUserId();
        this.username = user.getUsername();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
        this.roleName = user.getRole().getRoleName();
    }

}
