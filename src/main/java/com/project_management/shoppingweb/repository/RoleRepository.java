package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.dao.model.Role;
import com.project_management.shoppingweb.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
