package com.project_management.shoppingweb.service;

import com.project_management.shoppingweb.dao.interfaceValue.StatisticsModel;
import com.project_management.shoppingweb.dao.interfaceValue.TransPageModel;
import com.project_management.shoppingweb.dao.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface  TransactionService {
    /**
     * 查找
     * @param transPageModel
     * @return
     */
    public Page<Transaction> getByPage(TransPageModel transPageModel);

    public Object getByPageAd(TransPageModel transPageModel);

    /**
     * 新增
     * @param transaction
     * @return
     */
    public Transaction insert(Transaction transaction);

    /**
     * 更新
     * @param transaction
     * @return
     */
    public Transaction update(Transaction transaction);

    /**
     * 删除
     * @param transaction
     */
    public void delete(Transaction transaction);

    /**
     * 查找所有的代条目
     * @param transPageModel
     * @return
     */
    public Page<Transaction> getBuyByPage(TransPageModel transPageModel);

    /**
     * 查找所有的借
     * @param transPageModel
     * @return
     */
    public Page<Transaction> getSellByPage(TransPageModel transPageModel);

    public Object getStatistics(StatisticsModel statisticsModel);
    public Object getByPage1(TransPageModel transPageModel);

}
