package com.project_management.shoppingweb.service.Impl;

import com.project_management.shoppingweb.constant.HttpResponseConstants;
import com.project_management.shoppingweb.dao.model.DeBuy;
import com.project_management.shoppingweb.dao.model.User;
import com.project_management.shoppingweb.dao.vo.RequestResultVO;
import com.project_management.shoppingweb.repository.DeBuyRepository;
import com.project_management.shoppingweb.repository.UserRepository;
import com.project_management.shoppingweb.service.DeBuyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeBuyServiceImpl implements DeBuyService {
    @Resource
    private DeBuyRepository deBuyRepository;
    @Resource
    private UserRepository userRepository;

    @Override
    public DeBuy findByBuyId(Integer buyId) {
        return deBuyRepository.findByDeBuyId(buyId);
    }

    @Override
    public List<DeBuy> findByUserId(Integer userId) {
        return deBuyRepository.findByUserId(userId);
    }

    @Override
    public List<DeBuy> findAll() {
        return deBuyRepository.findAll();
    }

    @Override
    public Page<DeBuy> findAll(int start, int end) {
        return deBuyRepository.findAll(new PageRequest(start,end));
    }

    @Override
    public RequestResultVO insert(DeBuy deBuy) {
        //保存用户名,创建时间
        User user = userRepository.findByUserId(deBuy.getUserId());
        deBuy.setUserName(user.getUsername());
        deBuy.setSellTime(new Date());

        //存储，返回主键id
        deBuyRepository.save(deBuy);
        int deBuyId = deBuy.getDeBuyId();


        Map map = new HashMap<>();
        RequestResultVO requestResultVO = new RequestResultVO();
        requestResultVO.setCode(HttpResponseConstants.Public.SUCCESS_CODE);
        requestResultVO.setMessage(HttpResponseConstants.Public.SUCCESS_200);
        map.put("buyId",deBuyId);
        requestResultVO.setData(map);
        return requestResultVO;
    }

    @Override
    public RequestResultVO update(DeBuy deBuy) {
        DeBuy oldDeBuy = deBuyRepository.findByDeBuyId(deBuy.getDeBuyId());
        if(deBuy.getCredit()!=null){
            oldDeBuy.setCredit(deBuy.getCredit());
        }
        if(deBuy.getInterest()!=null){
            oldDeBuy.setInterest(deBuy.getInterest());
        }
        if(deBuy.getMoneyNum()!=null){
            oldDeBuy.setMoneyNum(deBuy.getMoneyNum());
        }
        if(deBuy.getPeriod()!=null){
            oldDeBuy.setPeriod(deBuy.getPeriod());
        }
        deBuyRepository.save(oldDeBuy);

        RequestResultVO requestResultVO = new RequestResultVO();
        requestResultVO.setCode(HttpResponseConstants.Public.SUCCESS_CODE);
        requestResultVO.setMessage(HttpResponseConstants.Public.SUCCESS_300);
        return requestResultVO;
    }
}
