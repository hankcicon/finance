package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByPhoneNumber(String phoneNumber);
    User findByUsernameAndPassword(String username,String password);
    User save(User user);
    List<User> findAll();
    User findByUserId(Integer userId);
    List<User> findByExpectedCredit(Integer expectedCredit);
}
