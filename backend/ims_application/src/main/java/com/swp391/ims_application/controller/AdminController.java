package com.swp391.ims_application.controller;

import com.swp391.ims_application.entity.User;
import com.swp391.ims_application.payload.CreateAccountRequest;
import com.swp391.ims_application.payload.CustomResponse;
import com.swp391.ims_application.service.imp.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    IUserService userService;

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        CustomResponse customResponse = new CustomResponse();
        HttpStatus status;

        User user = new User();
        user.setUsername(createAccountRequest.getUsername());
        user.setPassword(createAccountRequest.getPassword());
        user.setEmail(createAccountRequest.getEmail());
        user.setPhoneNumber(createAccountRequest.getPhoneNumber());
        user.setRole(createAccountRequest.getRole());
        boolean check = userService.createAccount(user);
        if(check) {
            customResponse.setMessage("Account created");
            customResponse.setStatus(HttpStatus.CREATED.value());
            customResponse.setData(user);
            customResponse.setSuccess(true);
            status = HttpStatus.CREATED;
        } else {
            customResponse.setMessage("Account creation failed");
            customResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            customResponse.setSuccess(false);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(customResponse, status);
    }


}
