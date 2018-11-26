package com.project_management.shoppingweb.controller;

import com.project_management.shoppingweb.constant.HttpResponseConstants;
import com.project_management.shoppingweb.dao.model.Message;
import com.project_management.shoppingweb.dao.model.Role;
import com.project_management.shoppingweb.dao.model.User;
import com.project_management.shoppingweb.dao.vo.RequestResultVO;
import com.project_management.shoppingweb.service.MessageService;
import com.project_management.shoppingweb.service.UserService;
import com.project_management.shoppingweb.util.message.SmsDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/getMessage")
    public Object getMessage(@RequestBody User user){
        String phoneNumber = user.getPhoneNumber();
//        String email = request.getParameter("email");
//        Map<String, Object> map = new HashMap<>();
//        User user = userService.findByUserName(username);
//
        RequestResultVO requestResultVO = new RequestResultVO();
//        if(!password.equals(password2)) {
//            requestResultVO.setCode(HttpResponseConstants.Public.ERROR_CODE);
//            requestResultVO.setMessage(HttpResponseConstants.Public.ERROR_904);
//            return requestResultVO;
//        }
//
//        if(user != null) {
//            requestResultVO.setCode(HttpResponseConstants.Public.ERROR_CODE);
//            requestResultVO.setMessage(HttpResponseConstants.Public.ERROR_903);
//            return requestResultVO;
//        }
//        //该手机号已被使用
//        User user1 = userService.findByPhoneNumber(phoneNumber);
//        if(user1 != null){
//            requestResultVO.setCode(HttpResponseConstants.Public.ERROR_CODE);
//            requestResultVO.setMessage(HttpResponseConstants.Public.ERROR_910);
//            return requestResultVO;
//        }

        //生成验证码
        int flag = new Random().nextInt(999999);
        if(flag<100000){
            flag += 100000;
        }
        String verificationCode = "" + flag;

        //发送验证码
        requestResultVO.setCode(HttpResponseConstants.Public.ERROR_CODE);
        requestResultVO.setMessage(HttpResponseConstants.Public.ERROR_907);

        try {
            String code = SmsDemo.sendSms(phoneNumber,verificationCode);
            if(code.equals("OK")){
                requestResultVO.setCode(HttpResponseConstants.Public.SUCCESS_CODE);
                requestResultVO.setMessage(HttpResponseConstants.Public.SUCCESS_601);

                Message oldMessage = messageService.findByPhoneNumber(phoneNumber);
                if(oldMessage != null){
                    messageService.delete(oldMessage);
                }
                Message message = new Message();
                message.setPhoneNumber(phoneNumber);
                message.setVerificationCode(verificationCode);
                messageService.save(message);
            }
        } catch (Exception e){
            return requestResultVO;
        }

        return requestResultVO;
    }

    @RequestMapping(value = "/getLoginMessage")
    public Object getLoginMessage(@RequestBody User user1){
        String username = user1.getUsername();

        RequestResultVO requestResultVO = new RequestResultVO();

        User user = userService.findByUserName(username);
        if(user==null){
            requestResultVO.setCode(HttpResponseConstants.Public.ERROR_CODE);
            requestResultVO.setMessage(HttpResponseConstants.Public.ERROR_901);
            return requestResultVO;
        }

        String phoneNumber = user.getPhoneNumber();

        //生成验证码
        int flag = new Random().nextInt(999999);
        if(flag<100000){
            flag += 100000;
        }
        String verificationCode = "" + flag;

        //发送验证码
        requestResultVO.setCode(HttpResponseConstants.Public.ERROR_CODE);
        requestResultVO.setMessage(HttpResponseConstants.Public.ERROR_907);

        try {
            String code = SmsDemo.sendSms(phoneNumber,verificationCode);
            if(code.equals("OK")){
                requestResultVO.setCode(HttpResponseConstants.Public.SUCCESS_CODE);
                requestResultVO.setMessage(HttpResponseConstants.Public.SUCCESS_601);
                Map map = new HashMap();
                map.put("phoneNumber",phoneNumber);
                requestResultVO.setData(map);

                Message oldMessage = messageService.findByPhoneNumber(phoneNumber);
                if(oldMessage != null){
                    messageService.delete(oldMessage);
                }
                Message message = new Message();
                message.setPhoneNumber(phoneNumber);
                message.setVerificationCode(verificationCode);
                messageService.save(message);
            }
        } catch (Exception e){
            return requestResultVO;
        }


        return requestResultVO;
    }
}
