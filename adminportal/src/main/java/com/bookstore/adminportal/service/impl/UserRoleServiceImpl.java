package com.bookstore.adminportal.service.impl;

import com.bookstore.adminportal.domain.security.UserRole;
import com.bookstore.adminportal.repository.UserRoleRepository;
import com.bookstore.adminportal.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;


    @Override
    public void save(UserRole userRole) {
        userRoleRepository.save(userRole);
    }
}
