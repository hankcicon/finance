package com.project_management.shoppingweb.service;

import com.project_management.shoppingweb.dao.model.Role;
import com.project_management.shoppingweb.dao.model.User;

public interface RoleService {
    /**
     * 根据用户名查找
     * @param name
     * @return
     */
    public Role findByName(String name);
}
