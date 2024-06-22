package com.swp391.ims_application.controller;

import com.swp391.ims_application.entity.User;
import com.swp391.ims_application.payload.UserDTO;
import com.swp391.ims_application.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImp userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users.stream().map(UserDTO::new).collect(Collectors.toList());
    }
}
