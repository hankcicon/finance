package com.project_management.shoppingweb.service;

import com.project_management.shoppingweb.dao.model.Card;
import com.project_management.shoppingweb.dao.vo.RequestResultVO;

import java.util.List;

public interface CardService {
    /**
     * 根据客户id查找
     * @param
     * @return
     */
    public List<Card> findByUserId(Integer userId);
    /**
     * 新增新增
     * @param
     * @return
     */
    public RequestResultVO insert(Card card);
    /**
     * 修改
     * @param
     * @return
     */
    public RequestResultVO update(Card card);
    /**
     * 删除
     * @param
     * @return
     */
    public RequestResultVO delete(Integer cardId);
}
