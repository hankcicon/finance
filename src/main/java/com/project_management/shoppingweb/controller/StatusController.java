package com.project_management.shoppingweb.controller;

import com.project_management.shoppingweb.dao.model.Card;
import com.project_management.shoppingweb.dao.model.Transaction;
import com.project_management.shoppingweb.dao.model.User;
import com.project_management.shoppingweb.service.TransactionService;
import com.project_management.shoppingweb.service.common.ResultBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {
    @Autowired
    private TransactionService transactionService;

    /**
     * 查询用户卡包信息
     */
    @RequestMapping(value = "/changeOO", method = RequestMethod.POST)
    public Object getByUserId(@RequestBody Transaction transaction){
        transaction.setBuyStatus("O");
        transaction.setSellStatus("O");
        transactionService.update(transaction);
        return ResultBuilder.buildSuccessResult();
    }
}
