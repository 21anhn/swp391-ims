package com.swp391.ims_application.controller;

import com.swp391.ims_application.entity.User;
import com.swp391.ims_application.payload.AccountDTO;
import com.swp391.ims_application.payload.CustomResponse;
import com.swp391.ims_application.payload.UserDTO;
import com.swp391.ims_application.service.SendMailService;
import com.swp391.ims_application.service.imp.IRoleService;
import com.swp391.ims_application.service.imp.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            List<UserDTO> userRespons = new ArrayList<>();
            for (User user : users) {
                UserDTO userDTO = new UserDTO();
                userDTO.setId(user.getUserId());
                userDTO.setUsername(user.getUsername());
                userDTO.setPassword(user.getPassword());
                userDTO.setPhoneNumber(user.getPhoneNumber());
                userDTO.setEmail(user.getEmail());
                if (user.getRole() != null) {
                    userDTO.setRoleName(user.getRole().getRoleName());
                }
                userRespons.add(userDTO);
            }
            customResponse.setData(userRespons);
        } else {
            status = HttpStatus.NOT_FOUND;
            customResponse.setMessage("No users found");
        }
        return new ResponseEntity<>(customResponse, status);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> searchByUsername(@PathVariable String username) {
        AccountDTO accountDTO = userService.getUserByUsername(username);
        if (accountDTO == null) {
            return new ResponseEntity<>("Not found username: " + username, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO accountDTO) {
        User user = userService.createAccount(accountDTO);
        if (user != null) {
            String content = "Username: " + user.getUsername()
                    + "\nPassword: " + user.getPassword()
                    + "\nEnter this link to login: https://tinyurl.com/tn4e64wm";
            sendMailService.sendMail(accountDTO.getEmail(), "Your account in Internship Management System", content);
            return new ResponseEntity<>("Account created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Account creation failed", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> resetPassword(@PathVariable String username) {
        boolean check = userService.resetPassword(username);
        if (check) {
            return new ResponseEntity<>("Reset password successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Reset password failed due to not found username: " + username, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> updateAccount(@PathVariable String username, @RequestParam boolean isActive) {
        boolean check = userService.lockAccount(username, isActive);
        if (check && !isActive) {
            return new ResponseEntity<>("Account locked", HttpStatus.OK);
        }
        return new ResponseEntity<>("Account not founded or not locked", HttpStatus.NOT_FOUND);
    }


    @GetMapping("/users/count-by-role")
    public ResponseEntity<Map<String, Long>> countUsersByRole() {
        Map<String, Long> roleCountMap = userService.countUsersByRole();
        return ResponseEntity.ok().body(roleCountMap);
    }

    @GetMapping("/users/count-total")
    public ResponseEntity<Long> countTotalUsers() {
        long totalUsers = userService.countTotalUsers();
        return ResponseEntity.ok().body(totalUsers);
    }
}
