package com.swp391.ims_application.controller;

import com.swp391.ims_application.entity.User;
import com.swp391.ims_application.payload.CustomResponse;
import com.swp391.ims_application.payload.UserResponse;
import com.swp391.ims_application.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UserServiceImp userServiceImp;

    @PostMapping
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        CustomResponse customResponse = new CustomResponse();
        User user = userServiceImp.login(username, password);
        //Nếu check username có trong DB và same password thì != null
        if (user != null) {
            UserResponse userResponse = new UserResponse(user.getUsername(), user.getPassword(), user.getFullName(), user.getDob()
                    , user.getGender(), user.getEmail(), user.getPhoneNumber(), user.getAddress(), user.getRole().getRoleName());

            customResponse.setStatus(200);
            customResponse.setMessage("Login successful");
            customResponse.setData(userResponse);
            customResponse.setSuccess(true);
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        }
        customResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        customResponse.setMessage("Login failed");
        customResponse.setSuccess(false);
        return new ResponseEntity<>(customResponse, HttpStatus.UNAUTHORIZED);
    }
}
