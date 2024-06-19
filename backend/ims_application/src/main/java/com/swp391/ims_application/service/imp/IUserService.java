package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.entity.User;

import java.util.List;

public interface IUserService {
    User login(String username, String password);

    boolean createAccount(User user);

    List<User> getAllUsers();
}
