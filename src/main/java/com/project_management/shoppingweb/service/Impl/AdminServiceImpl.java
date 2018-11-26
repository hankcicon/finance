package com.project_management.shoppingweb.service.Impl;


import com.project_management.shoppingweb.dao.model.Admin;
import com.project_management.shoppingweb.repository.AdminRepository;
import com.project_management.shoppingweb.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService{
	@Resource
    AdminRepository adminRepository ;

	/**
	 * 根据用户名和密码进行查找
	 * @param username
	 * @param password
	 * @return
	 */
	public Admin findByAdministerNameAndPassword(String username, String password) {
		return adminRepository.findByAdministerNameAndPassword(username,password);
	}
	
	public Admin findByAdministerName(String username) {
		return adminRepository.findByAdministerName(username);
	}

	public Admin save(Admin admin){
		return adminRepository.save(admin);
	}
	
	
}
