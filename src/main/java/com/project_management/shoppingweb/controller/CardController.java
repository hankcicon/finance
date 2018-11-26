package com.project_management.shoppingweb.controller;

import com.project_management.shoppingweb.dao.model.Card;
import com.project_management.shoppingweb.dao.model.User;
import com.project_management.shoppingweb.service.CardService;
import com.project_management.shoppingweb.service.common.ResultBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    /**
     * 查询用户卡包信息
     */
    @RequestMapping(value = "/getByUserId", method = RequestMethod.POST)
    public Object getByUserId(@RequestBody User user){
        int userId = user.getUserId();
        List<Card> cards = cardService.findByUserId(userId);
        return ResultBuilder.buildSuccessResult(cards);
    }

    /**
     * 新增卡包信息
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Object insert(@RequestBody Card card){
        return cardService.insert(card);
    }

    /**
     * 修改卡包信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(@RequestBody Card card){
        return cardService.update(card);
    }

    /**
     * 删除卡包信息
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(@RequestBody Card card){
        return cardService.delete(card.getCardId());
    }

}
