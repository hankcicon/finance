package com.project_management.shoppingweb.controller;

import com.project_management.shoppingweb.config.WebSecurityConfig;
import com.project_management.shoppingweb.dao.model.Admin;
import com.project_management.shoppingweb.service.AdminService;
import com.project_management.shoppingweb.util.MD5Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@Controller
//@RequestMapping("/admin")
public class AdminController {
    private static String PASSWORD_KEY = "@#$%^&*()OPG#$%^&*(HG";
//
    private Logger logger = Logger.getLogger(this.getClass());
////
    @Autowired
    private AdminService adminService;
//    /**
//     * 跳转到注册界面
//     * @return
//     */
//    @GetMapping("/register")
//    public String register(){
//        return "admin/register";
//    }
//
//    /**
//     * 跳转登陆界面
//      * @return
//     */
//    @GetMapping("/login")
//    public String login(){
//        return "admin/login";
//    }

//    /**
//     * 登录
//     * @param request
//     * @param session
//     * @param model
//     * @return
//     * @throws ServletException
//     * @throws IOException
//     */
//    @RequestMapping(value = "/addlogin", method = RequestMethod.POST)
//    public String addlogin(HttpServletRequest request,HttpSession session,Model model)  throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        Map<String, Object> map = new HashMap<>();
//        Admin admin = adminService.findByAdministerNameAndPassword(username, getPwd(password));
//        if(admin == null) {
//            map.put("success", false);
//            map.put("message", "密码错误");
//            return "redirect:/admin/login";
//        }
//        else{
//            map.put("success", true);
//            map.put("message", "登录成功");
//            model.addAttribute("adminUserName", username);
//            model.addAttribute("adminId", admin.getAdministerId());
//        }
//
//        return "redirect:/admin/adsManagement";
//
//    }
//
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        return "redirect:/admin/login";
//    }
//
//    @RequestMapping(value = "/addregister", method = RequestMethod.POST)
//    public String addregist(HttpServletRequest request){
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String password2 = request.getParameter("password2");
//        String email = request.getParameter("email");
//        Map<String, Object> map = new HashMap<>();
//        Admin admin = adminService.findByAdministerName(username);
//        /*
//         * 最好前端做判断
//         * */
//        if(!password.equals(password2)) {
//            map.put("success", false);
//            map.put("message", "两次密码不相同");
//            return "redirect:/admin/register";
//        }
//
//        if(admin != null) {
//            map.put("success", false);
//            map.put("message", "用户已存在");
//            return "redirect:/admin/register";
//        }
//
//        admin = new Admin();
//
//        admin.setAdministerName(username);
//        admin.setPassword(getPwd(password));
//        admin.setEmail(email);
//
//        adminService.save(admin);
//        map.put("success", true);
//        map.put("message", "注册成功");
//        return "redirect:/admin/login";
//    }
//
//
//    private String getPwd(String password){
//        try {
//            String pwd = MD5Util.encrypt(password+PASSWORD_KEY);
//            return pwd;
//        } catch (Exception e) {
//            logger.error("密码加密异常：",e);
//        }
//        return null;
//    }
}
