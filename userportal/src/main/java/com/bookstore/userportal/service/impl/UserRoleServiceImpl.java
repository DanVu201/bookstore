package com.bookstore.userportal.service.impl;

import com.bookstore.userportal.domain.security.UserRole;
import com.bookstore.userportal.repository.UserRoleRepository;
import com.bookstore.userportal.service.UserRoleService;
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
