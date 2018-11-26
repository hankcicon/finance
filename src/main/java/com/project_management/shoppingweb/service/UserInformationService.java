package com.project_management.shoppingweb.service;

import com.project_management.shoppingweb.dao.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserInformationService {
    /**
     * 根据用户id查找
     * @param
     * @return
     */
    public User findByUserId(Integer userId);
    /**
     * 查找所有客户
     * @param
     * @return
     */
    public List<User> findAll();

    public Page<User> findAll(Integer pageNow, Integer pageSize);
    /**
     * 更新客户信息
     * @param
     * @return
     */
    public void updateUser(User user);

}
