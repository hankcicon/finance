package com.project_management.shoppingweb.service;

import com.project_management.shoppingweb.dao.model.DeBuy;
import com.project_management.shoppingweb.dao.model.DeSell;
import com.project_management.shoppingweb.dao.vo.RequestResultVO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DeBuyService {
    /**
     * 根据id查找
     * @param
     * @return
     */
    public DeBuy findByBuyId(Integer buyId);
    /**
     * 根据用户id查找
     * @param
     * @return
     */
    public List<DeBuy> findByUserId(Integer userId);
    /**
     * 查找所有
     * @param
     * @return
     */
    public List<DeBuy> findAll();
    public Page<DeBuy> findAll(int start, int end);
    /**
     * 新增待借
     * @param
     * @return
     */
    public RequestResultVO insert(DeBuy deBuy);
    /**
     * 修改待借
     * @param
     * @return
     */
    public RequestResultVO update(DeBuy deBuy);
}
