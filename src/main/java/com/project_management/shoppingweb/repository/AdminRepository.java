package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.dao.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
	Admin findByAdministerNameAndPassword(String username, String password);
	Admin findByEmailAndPassword(String email, String password);
	Admin findByAdministerName(String username);
	Admin save(Admin admin);
}
