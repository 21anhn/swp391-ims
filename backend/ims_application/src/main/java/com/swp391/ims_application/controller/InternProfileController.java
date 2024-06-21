package com.swp391.ims_application.controller;

import com.swp391.ims_application.payload.UserDTO;
import com.swp391.ims_application.service.imp.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interns")
public class InternProfileController {

    @Autowired
    private IUserService userService;

    @GetMapping("/{campaignId}")
    public ResponseEntity<?> getUser(@PathVariable int campaignId) {
        List<UserDTO> userDTOList = userService.viewAllProfileInterns(campaignId);
        if(userDTOList == null) {
            return new ResponseEntity<>("Not found any profile intern in campaign id: " + campaignId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    @GetMapping("/{campaignId}/{username}")
    public ResponseEntity<?> getProfile(@PathVariable int campaignId, @PathVariable String username) {
        UserDTO userDTO = userService.searchProfileIntern(username, campaignId);
        if (userDTO != null) {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found profile intern with username: " + username + " in campaign id: " + campaignId, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{campaignId}")
    public ResponseEntity<?> updateProfile(@PathVariable int campaignId, @RequestBody UserDTO userDTO) {
        boolean check = userService.editProfile(userDTO, campaignId);
        if(check) {
            return new ResponseEntity<>("Successfully updated profile for intern!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed due to some error...", HttpStatus.NOT_FOUND);
    }
}
