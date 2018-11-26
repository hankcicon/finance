package com.project_management.shoppingweb.service;

import com.project_management.shoppingweb.dao.model.Message;
import com.project_management.shoppingweb.dao.model.Role;
import com.project_management.shoppingweb.dao.model.User;

public interface MessageService {
    /**
     * 根据用户名查找
     * @return
     */
    public Message findByPhoneNumber(String phongNumber);
    /**
     * 删除
     * @return
     */
    public void delete(Message message);
    /**
     * 添加
     * @param
     * @return
     */
    public Message save(Message message);
}
