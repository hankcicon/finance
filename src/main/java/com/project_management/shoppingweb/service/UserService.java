package com.project_management.shoppingweb.service;

import com.project_management.shoppingweb.dao.model.Role;
import com.project_management.shoppingweb.dao.model.User;

import java.util.List;

public interface UserService {
    /**
     * 根据用户名和密码进行查找
     * @param username
     * @param password
     * @return
     */
    public User findByUserNameAndPassword(String username, String password);

    /**
     * 根据手机号查找
     * @param
     * @return
     */
    public User findByPhoneNumber(String phoneNumber);

    /**
     * 根据用户名查找
     * @param username
     * @return
     */
    public User findByUserName(String username);

    /**
     * 添加用户
     * @param
     * @return
     */
    public User save(User user);

}
