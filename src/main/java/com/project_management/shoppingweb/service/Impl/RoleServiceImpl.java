package com.project_management.shoppingweb.service.Impl;

import com.project_management.shoppingweb.dao.model.Role;
import com.project_management.shoppingweb.repository.RoleRepository;
import com.project_management.shoppingweb.repository.UserRepository;
import com.project_management.shoppingweb.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleRepository roleRepository ;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
