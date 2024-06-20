package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.Role;
import com.swp391.ims_application.entity.repository.RoleRepository;
import com.swp391.ims_application.service.imp.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByRoleName(name);
    }
}
