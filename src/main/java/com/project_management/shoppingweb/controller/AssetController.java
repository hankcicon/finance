package com.project_management.shoppingweb.controller;

import com.project_management.shoppingweb.dao.model.Asset;
import com.project_management.shoppingweb.dao.model.User;
import com.project_management.shoppingweb.service.AssetService;
import com.project_management.shoppingweb.service.common.ResultBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/asset")
public class AssetController {
    @Autowired
    private AssetService assetService;

    /**
     * 查询用户资产信息
     */
    @RequestMapping(value = "/getByUserId", method = RequestMethod.POST)
    public Object getByUserId(@RequestBody User user){
        int userId = user.getUserId();
        List<Asset> assets = assetService.findByUserId(userId);
        return ResultBuilder.buildSuccessResult(assets);
    }

    /**
     * 新增资产信息
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Object insert(@RequestBody Asset asset){
        return assetService.insert(asset);
    }

    /**
     * 修改资产信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(@RequestBody Asset asset){
        return assetService.update(asset);
    }

    /**
     * 删除资产信息
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(@RequestBody Asset asset){
        return assetService.delete(asset.getAssetId());
    }

}
