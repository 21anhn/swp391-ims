package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.entity.User;

import java.util.List;

public interface IUserService {
    public User login(String username, String password);

    public boolean createAccount(User user);

    public List<User> getAllUsers();
}
