package com.project_management.shoppingweb.service;

import com.project_management.shoppingweb.dao.model.Admin;

public interface AdminService {
    /**
     * 根据用户名和密码进行查找
     * @param username
     * @param password
     * @return
     */
    public Admin findByAdministerNameAndPassword(String username, String password);

    /**
     * 根据用户名查找
     * @param username
     * @return
     */
    public Admin findByAdministerName(String username);

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    public Admin save(Admin admin);
}
