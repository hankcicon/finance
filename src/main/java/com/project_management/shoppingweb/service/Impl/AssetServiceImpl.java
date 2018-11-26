package com.project_management.shoppingweb.service.Impl;

import com.project_management.shoppingweb.constant.HttpResponseConstants;
import com.project_management.shoppingweb.dao.model.Asset;
import com.project_management.shoppingweb.dao.vo.RequestResultVO;
import com.project_management.shoppingweb.repository.AssetRepository;
import com.project_management.shoppingweb.service.AssetService;
import com.project_management.shoppingweb.service.common.ResultBuilder;
import org.apache.http.client.methods.RequestBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssetServiceImpl implements AssetService {
    @Resource
    private AssetRepository assetRepository;

    @Override
    public List<Asset> findByUserId(Integer userId) {
        return assetRepository.findByUserId(userId);
    }

    @Override
    public RequestResultVO insert(Asset asset) {
        assetRepository.save(asset);

        Map map = new HashMap<>();
        RequestResultVO requestResultVO = new RequestResultVO();
        requestResultVO.setCode(HttpResponseConstants.Public.SUCCESS_CODE);
        requestResultVO.setMessage(HttpResponseConstants.Public.SUCCESS_200);
        map.put("AssetId",asset.getAssetId());
        requestResultVO.setData(map);

        return requestResultVO;
    }

    @Override
    public RequestResultVO update(Asset asset) {
        Asset oldAsset = assetRepository.findByAssetId(asset.getAssetId());
        if(asset.getAssessCertification()!=null){
            oldAsset.setAssessCertification(asset.getAssessCertification());
        }
        if(asset.getAssessmenValue()!=null){
            oldAsset.setAssessmenValue(asset.getAssessmenValue());
        }
        if(asset.getAssetType()!=null){
            oldAsset.setAssetType(asset.getAssetType());
        }
        if(asset.getUserId()!=null){
            oldAsset.setUserId(asset.getUserId());
        }

        assetRepository.save(oldAsset);

        RequestResultVO requestResultVO = new RequestResultVO();
        requestResultVO.setCode(HttpResponseConstants.Public.SUCCESS_CODE);
        requestResultVO.setMessage(HttpResponseConstants.Public.SUCCESS_300);
        return requestResultVO;
    }

    @Override
    public RequestResultVO delete(Integer assetId) {
        Asset oldAsset = assetRepository.findByAssetId(assetId);
        assetRepository.delete(oldAsset);

        RequestResultVO requestResultVO = new RequestResultVO();
        requestResultVO.setCode(HttpResponseConstants.Public.SUCCESS_CODE);
        requestResultVO.setMessage(HttpResponseConstants.Public.SUCCESS_400);
        return requestResultVO;
    }
}
