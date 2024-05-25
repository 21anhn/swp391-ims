package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.entity.User;

public interface IUserService {
    public User login(String username, String password);
}
