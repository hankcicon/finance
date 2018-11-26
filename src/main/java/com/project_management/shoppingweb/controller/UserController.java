package com.project_management.shoppingweb.controller;

import com.project_management.shoppingweb.constant.HttpResponseConstants;
import com.project_management.shoppingweb.dao.model.LoginUser;
import com.project_management.shoppingweb.dao.model.Message;
import com.project_management.shoppingweb.dao.model.Role;
import com.project_management.shoppingweb.dao.model.User;
import com.project_management.shoppingweb.dao.vo.RequestResultVO;
import com.project_management.shoppingweb.service.MessageService;
import com.project_management.shoppingweb.service.RoleService;
import com.project_management.shoppingweb.service.UserService;
import com.project_management.shoppingweb.service.common.ResultBuilder;
import com.project_management.shoppingweb.util.MD5Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/admin")
public class UserController {
    private static String PASSWORD_KEY = "@#$%^&*()OPG#$%^&*(HG";

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MessageService messageService;
    /**
     * 登录
     * @param request
     * @param session
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/addlogin", method = RequestMethod.POST)
    public Object addlogin(HttpServletRequest request, HttpSession session)  throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map<String, Object> map = new HashMap<>();
        User user = userService.findByUserNameAndPassword(username, getPwd(password));
        RequestResultVO requestResultVO = new RequestResultVO();
        if(user == null) {
            requestResultVO.setCode(HttpResponseConstants.Public.ERROR_CODE);
            requestResultVO.setMessage(HttpResponseConstants.Public.ERROR_901);
        }
        else{
            requestResultVO.setCode(HttpResponseConstants.Public.SUCCESS_CODE);
            requestResultVO.setMessage(HttpResponseConstants.Public.SUCCESS_600);
            map.put("username",username);
            requestResultVO.setData(map);
        }
        return requestResultVO;
    }

    /**
     * 登录返回结果
     * @param request
     * @param session
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/loginReturn", method = RequestMethod.POST)
    public Object loginReturn(HttpServletRequest request, HttpSession session)  throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        String identity = request.getParameter("role");
        String verificationCode = request.getParameter("verificationCode");
        RequestResultVO requestResultVO = new RequestResultVO();
        Map map = new HashMap();
        User user = userService.findByUserName(username);
        if(user == null){
            requestResultVO.setCode(HttpResponseConstants.Public.ERROR_CODE);
            requestResultVO.setMessage(HttpResponseConstants.Public.ERROR_901);
            return requestResultVO;
        }
        if(!user.getPassword().equals(getPwd(password))){
            requestResultVO.setCode(HttpResponseConstants.Public.ERROR_CODE);
            requestResultVO.setMessage(HttpResponseConstants.Public.ERROR_902);
            return requestResultVO;
        }
        String phoneNumber = user.getPhoneNumber();
        Message message = messageService.findByPhoneNumber(phoneNumber);
        if(message == null){
            requestResultVO.setCode(HttpResponseConstants.Public.ERROR_CODE);
            requestResultVO.setMessage(HttpResponseConstants.Public.ERROR_908);
            return requestResultVO;
        }
        if(!message.getVerificationCode().equals(verificationCode)){
            requestResultVO.setCode(HttpResponseConstants.Public.ERROR_CODE);
            requestResultVO.setMessage(HttpResponseConstants.Public.ERROR_909);
            return requestResultVO;
        }

        List<Role> roles = user.getRoles();
//        for(Role role : roles){
//            if(role.getName().equals(identity)){
//                isRightRole = true;
//            }
//        }
//
//
//        if(isRightRole == false) {
//            requestResultVO.setCode(HttpResponseConstants.Public.ERROR_CODE);
//            requestResultVO.setMessage(HttpResponseConstants.Public.ERROR_905);
//        }
//        else{
            requestResultVO.setCode(HttpResponseConstants.Public.SUCCESS_CODE);
            requestResultVO.setMessage(HttpResponseConstants.Public.SUCCESS_600);
            map.put("username",username);
            if(!roles.isEmpty()){
                Role role = roles.get(0);
                map.put("role",role.getName());
            }
            map.put("userId",user.getUserId());
            requestResultVO.setData(map);
//        }
        return requestResultVO;
    }

    /**
     * 注销
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Object logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = null;
        if(auth != null){
            username = auth.getName();
            new SecurityContextLogoutHandler().logout(request,response,auth);
        }
        return ResultBuilder.buildSuccessResult(HttpResponseConstants.Public.SUCCESS_700,username);
    }

    /**
     * 注销返回
     */
    @RequestMapping(value = "/logoutReturn")
    public Object logoutReturn(){
        RequestResultVO requestResultVO = new RequestResultVO();
        requestResultVO.setCode(HttpResponseConstants.Public.SUCCESS_CODE);
        requestResultVO.setMessage(HttpResponseConstants.Public.SUCCESS_700);
        return requestResultVO;
    }

    @RequestMapping(value = "/addregister")
    public Object addregist(@RequestBody LoginUser loginUser){
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        String roleString = loginUser.getRole();
        String phoneNumber = loginUser.getPhoneNumber();
        String verificationCode = loginUser.getVerificationCode();

        Map<String, Object> map = new HashMap<>();
        User user = userService.findByUserName(username);


        RequestResultVO requestResultVO = new RequestResultVO();
        //验证用户名是否已存在
        if(user != null) {
            requestResultVO.setCode(HttpResponseConstants.Public.ERROR_CODE);
            requestResultVO.setMessage(HttpResponseConstants.Public.ERROR_903);
            return requestResultVO;
        }

        //判断验证码是否正确
        Message message = messageService.findByPhoneNumber(phoneNumber);
        if(message == null){
            requestResultVO.setCode(HttpResponseConstants.Public.ERROR_CODE);
            requestResultVO.setMessage(HttpResponseConstants.Public.ERROR_908);
            return requestResultVO;
        }
        if(!message.getVerificationCode().equals(verificationCode)){
            requestResultVO.setCode(HttpResponseConstants.Public.ERROR_CODE);
            requestResultVO.setMessage(HttpResponseConstants.Public.ERROR_909);
            return requestResultVO;
        }

        user = new User();
        user.setUsername(username);
        user.setPassword(getPwd(password));
        user.setPhoneNumber(phoneNumber);
        user.setCreateTime(new Date());
        user.setPwdEfficitiveDay(new Date());

        List<Role> roles = new ArrayList<Role>();
        Role identity = roleService.findByName(roleString);
        roles.add(identity);
        if(identity == null){
            requestResultVO.setCode(HttpResponseConstants.Public.ERROR_CODE);
            requestResultVO.setMessage(HttpResponseConstants.Public.ERROR_906);
            return requestResultVO;
        }
        user.setRoles(roles);

        userService.save(user);

        map.put("userId",user.getUserId());
        map.put("role",roleString);
        requestResultVO.setCode(HttpResponseConstants.Public.SUCCESS_CODE);
        requestResultVO.setMessage(HttpResponseConstants.Public.SUCCESS_200);
        requestResultVO.setData(map);

        return requestResultVO;
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

    @RequestMapping(value = "/loginFailReturn", method = RequestMethod.POST)
    public Object loginFailReturn()  throws ServletException, IOException {
        RequestResultVO requestResultVO = new RequestResultVO();
        requestResultVO.setCode(HttpResponseConstants.Public.ERROR_CODE);
        requestResultVO.setMessage(HttpResponseConstants.Public.ERROR_911);
        return requestResultVO;
    }

}
