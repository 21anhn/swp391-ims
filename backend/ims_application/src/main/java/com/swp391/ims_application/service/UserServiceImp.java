package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.User;
import com.swp391.ims_application.repository.UserRepository;
import com.swp391.ims_application.service.imp.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
