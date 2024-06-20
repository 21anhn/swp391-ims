package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.entity.User;
import com.swp391.ims_application.payload.AccountDTO;

import java.util.List;

public interface IUserService {
    User login(String username, String password);

    boolean createAccount(User user);

    List<User> getAllUsers();

    AccountDTO getUserByUsername(String username);

    boolean resetPassword(String username);

    boolean lockAccount(String username, boolean isActive);


}
