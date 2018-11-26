package com.project_management.shoppingweb.service.Impl;

import com.project_management.shoppingweb.dao.model.User;
import com.project_management.shoppingweb.repository.UserRepository;
import com.project_management.shoppingweb.service.UserInformationService;
import com.project_management.shoppingweb.util.MD5Util;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserInformationImpl implements UserInformationService {
    @Resource
    private UserRepository userRepository;

    private static String PASSWORD_KEY = "@#$%^&*()OPG#$%^&*(HG";

    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public User findByUserId(Integer userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Integer pageNow, Integer pageSize) {
        return userRepository.findAll(new PageRequest(pageNow,pageSize));
    }

    @Override
    public void updateUser(User user) {
        int userId = user.getUserId();
        User oldUser = this.findByUserId(userId);
        if(user.getPassword()!=null){
            oldUser.setPassword(this.getPwd(user.getPassword()));
            oldUser.setPwdModifyDate(new Date());
        }
        if(user.getCard()!=null){
            oldUser.setCard(user.getCard());
        }
        if(user.getDelayTransation()!=null){
            oldUser.setDelayTransation(user.getDelayTransation());
        }
        if(user.getDeposit()!=null){
            oldUser.setDeposit(user.getDeposit());
        }
        if(user.getDescription()!=null){
            oldUser.setDescription(user.getDescription());
        }
        if(user.getEmail()!=null){
            oldUser.setEmail(user.getEmail());
        }
        if(user.getIsDeleted()!=null){
            oldUser.setIsDeleted(user.getIsDeleted());
        }
        if(user.getPwdEfficitiveDay()!=null){
            oldUser.setPwdEfficitiveDay(user.getPwdEfficitiveDay());
        }
        if(user.getPwdModifyDate()!=null){
            oldUser.setPwdModifyDate(user.getPwdModifyDate());
        }
        if(user.getSignature()!=null){
            oldUser.setSignature(user.getSignature());
        }
        if(user.getStatus()!=null){
            oldUser.setStatus(user.getStatus());
        }
        if(user.getTransationNum()!=null){
            oldUser.setTransationNum(user.getTransationNum());
        }
        if(user.getType()!=null){
            oldUser.setType(user.getType());
        }
        if(user.getAnnualIncome()!=null){
            oldUser.setAnnualIncome(user.getAnnualIncome());
        }
        if(user.getBirthDate()!=null){
            oldUser.setBirthDate(user.getBirthDate());
        }
        if(user.getGender()!=null){
            oldUser.setGender(user.getGender());
        }
        if(user.getJob()!=null){
            oldUser.setJob(user.getJob());
        }
        if(user.getIdNum()!=null){
            oldUser.setIdNum(user.getIdNum());
        }
        if(user.getIdType()!=null){
            oldUser.setIdType(user.getIdType());
        }
        if(user.getPhoneNumber()!=null){
            oldUser.setPhoneNumber(user.getPhoneNumber());
        }
        if(user.getExpectedCredit()!=null){
            oldUser.setExpectedCredit(user.getExpectedCredit());
        }
        if(user.getCredit()!=null){
            oldUser.setCredit(user.getCredit());
        }
        oldUser.setAmendTime(new Date());

        userRepository.save(oldUser);
    }

    private String getPwd(String password){
        try {
            String pwd = MD5Util.encrypt(password+PASSWORD_KEY);
            return pwd;
        } catch (Exception e) {
            logger.error("密码加密异常：",e);
        }
        return null;
    }
}
