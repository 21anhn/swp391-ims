package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.User;
import com.swp391.ims_application.entity.repository.UserRepository;
import com.swp391.ims_application.payload.AccountDTO;
import com.swp391.ims_application.service.imp.IUserService;
import com.swp391.ims_application.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SendMailService sendMailService;

    @Override
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public boolean createAccount(User user) {
        if (user != null) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public AccountDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setRoleName(user.getRole().getRoleName());
            accountDTO.setUsername(user.getUsername());
            accountDTO.setPassword(user.getPassword());
            accountDTO.setEmail(user.getEmail());
            accountDTO.setPhoneNumber(user.getPhoneNumber());
            return accountDTO;
        }
        return null;
    }

    @Override
    public boolean resetPassword(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            String password = Helper.generatePassword();
            user.setPassword(password);
            userRepository.save(user);
            sendMailService.sendMail(user.getEmail(), "Reset password", "New password: " + user.getPassword() + "\nEnter this url to login: " + "https://tinyurl.com/tn4e64wm");
            return true;
        }
        return false;
    }

    @Override
    public boolean lockAccount(String username, boolean isActive) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setActive(isActive);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
