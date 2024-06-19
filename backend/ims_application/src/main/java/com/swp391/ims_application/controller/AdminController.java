package com.swp391.ims_application.controller;

import com.swp391.ims_application.entity.Role;
import com.swp391.ims_application.entity.User;
import com.swp391.ims_application.payload.AccountDTO;
import com.swp391.ims_application.payload.CustomResponse;
import com.swp391.ims_application.payload.UserResponse;
import com.swp391.ims_application.service.SendMailService;
import com.swp391.ims_application.service.imp.IRoleService;
import com.swp391.ims_application.service.imp.IUserService;
import com.swp391.ims_application.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    IUserService userService;

    @Autowired
    IRoleService roleService;

    @Autowired
    SendMailService sendMailService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        CustomResponse customResponse = new CustomResponse();
        HttpStatus status;

        List<User> users = userService.getAllUsers();
        if (!users.isEmpty()) {
            status = HttpStatus.OK;
            customResponse.setMessage("Successfully retrieved all users");
            List<UserResponse> userResponses = new ArrayList<>();
            for (User user : users) {
                UserResponse userResponse = new UserResponse();
                userResponse.setId(user.getUserId());
                userResponse.setUsername(user.getUsername());
                userResponse.setPassword(user.getPassword());
                userResponse.setPhoneNumber(user.getPhoneNumber());
                userResponse.setEmail(user.getEmail());
                if (user.getRole() != null) {
                    userResponse.setRoleName(user.getRole().getRoleName());
                }
                userResponses.add(userResponse);
            }
            customResponse.setData(userResponses);
        } else {
            status = HttpStatus.NOT_FOUND;
            customResponse.setMessage("No users found");
        }
        return new ResponseEntity<>(customResponse, status);
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO accountDTO) {
        CustomResponse customResponse = new CustomResponse();
        HttpStatus status;
        String password = Helper.generatePassword(); //Auto generate password

        User user = new User();
        user.setUsername(accountDTO.getUsername());
        user.setPassword(password);
        user.setEmail(accountDTO.getEmail());
        user.setPhoneNumber(accountDTO.getPhoneNumber());
        Role role = roleService.getRoleByName(accountDTO.getRoleName());
        user.setRole(role);
        boolean check = userService.createAccount(user);
        if (check) {
            customResponse.setMessage("Account created");
            customResponse.setStatus(HttpStatus.CREATED.value());
            customResponse.setSuccess(true);
            status = HttpStatus.CREATED;
            String content = "Username: " + accountDTO.getUsername()
                    + "\nPassword: " + password
                    + "\nEnter this link to login: https://tinyurl.com/tn4e64wm";
            sendMailService.sendMail(accountDTO.getEmail(), "Your account in Internship Management System", content);


        } else {
            customResponse.setMessage("Account creation failed");
            customResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            customResponse.setSuccess(false);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(customResponse, status);
    }


}
