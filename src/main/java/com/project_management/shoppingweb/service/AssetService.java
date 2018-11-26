package com.project_management.shoppingweb.service;

import com.project_management.shoppingweb.dao.model.Asset;
import com.project_management.shoppingweb.dao.model.DeBuy;
import com.project_management.shoppingweb.dao.vo.RequestResultVO;

import java.util.List;

public interface AssetService {
    /**
     * 根据客户id查找
     * @param
     * @return
     */
    public List<Asset> findByUserId(Integer userId);
    /**
     * 新增新增
     * @param
     * @return
     */
    public RequestResultVO insert(Asset asset);
    /**
     * 修改
     * @param
     * @return
     */
    public RequestResultVO update(Asset asset);
    /**
     * 删除
     * @param
     * @return
     */
    public RequestResultVO delete(Integer assetId);
}
