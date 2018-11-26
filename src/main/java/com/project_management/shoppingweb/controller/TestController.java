package com.project_management.shoppingweb.controller;

import com.project_management.shoppingweb.constant.HttpResponseConstants;
import com.project_management.shoppingweb.dao.model.Transaction;
import com.project_management.shoppingweb.dao.model.User;
import com.project_management.shoppingweb.dao.vo.RequestResultVO;
import com.project_management.shoppingweb.repository.TransactionRepository;
import com.project_management.shoppingweb.repository.UserRepository;
import com.project_management.shoppingweb.service.common.ResultBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    public Object addlogin(HttpServletRequest request, HttpSession session) throws ServletException, IOException {
        String username = request.getParameter("username");
        return ResultBuilder.buildSuccessResult(username);
    }
    @RequestMapping(value = "/test2",method = RequestMethod.POST)
    public Object test(){
        List<Transaction> transactionList=transactionRepository.findAll();
        for(Transaction transaction:transactionList){
            transaction.setBuyerName(userRepository.findByUserId(transaction.getBuyId()).getUsername());
            transaction.setSellerName(userRepository.findByUserId(transaction.getSellId()).getUsername());
            //transactionRepository.save(transaction);
        }
        transactionRepository.save(transactionList);
        return null;
    }
}
