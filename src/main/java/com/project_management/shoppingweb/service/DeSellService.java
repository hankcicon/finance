package com.project_management.shoppingweb.service;

import com.project_management.shoppingweb.dao.model.DeSell;
import com.project_management.shoppingweb.dao.model.User;
import com.project_management.shoppingweb.dao.vo.RequestResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeSellService {
    /**
     * 根据id查找
     * @param
     * @return
     */
    public DeSell findBySellId(Integer sellId);
    /**
     * 根据用户id查找
     * @param
     * @return
     */
    public List<DeSell> findByUserId(Integer userId);
    /**
     * 查找所有
     * @param
     * @return
     */
    public List<DeSell> findAll();
    public Page<DeSell> findAll(int start,int end);
    /**
     * 新增待贷
     * @param
     * @return
     */
    public RequestResultVO insert(DeSell deSell);
    /**
     * 修改待贷
     * @param
     * @return
     */
    public RequestResultVO update(DeSell deSell);
}
