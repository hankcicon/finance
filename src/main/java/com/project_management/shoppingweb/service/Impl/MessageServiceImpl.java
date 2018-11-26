package com.project_management.shoppingweb.service.Impl;

import com.project_management.shoppingweb.dao.model.Message;
import com.project_management.shoppingweb.repository.MessageRepository;
import com.project_management.shoppingweb.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageServiceImpl implements MessageService {
    @Resource
    MessageRepository messageRepository ;

    @Override
    public Message findByPhoneNumber(String phoneNumber) {
        return messageRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public void delete(Message message) {
        messageRepository.delete(message);
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }
}
