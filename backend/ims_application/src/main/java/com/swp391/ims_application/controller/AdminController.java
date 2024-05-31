package com.swp391.ims_application.controller;

import com.swp391.ims_application.entity.Role;
import com.swp391.ims_application.entity.User;
import com.swp391.ims_application.payload.AccountRequest;
import com.swp391.ims_application.payload.CustomResponse;
import com.swp391.ims_application.payload.UserResponse;
import com.swp391.ims_application.service.imp.IRoleService;
import com.swp391.ims_application.service.imp.IUserService;
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
    IRoleService roleService;

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
                userResponse.setUsername(user.getUsername());
                userResponse.setPassword(user.getPassword());
                if(user.getRole() != null) {
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
    public ResponseEntity<?> createAccount(@RequestBody AccountRequest accountRequest) {
        CustomResponse customResponse = new CustomResponse();
        HttpStatus status;

        User user = new User();
        user.setUsername(accountRequest.getUsername());
        user.setPassword(accountRequest.getPassword());
        user.setEmail(accountRequest.getEmail());
        user.setPhoneNumber(accountRequest.getPhoneNumber());
        Role role = roleService.getRoleByName(accountRequest.getRoleName());
        System.out.println(role.getRoleId() + " " + role.getRoleName());
        user.setRole(role);
        boolean check = userService.createAccount(user);
        if(check) {
            customResponse.setMessage("Account created");
            customResponse.setStatus(HttpStatus.CREATED.value());
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
