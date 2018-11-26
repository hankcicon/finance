package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.dao.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    Message findByPhoneNumber(String phoneNumber);

    void delete(Message message);

    Message save(Message message);
}
