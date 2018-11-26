package com.project_management.shoppingweb.service.Impl;

import com.project_management.shoppingweb.constant.HttpResponseConstants;
import com.project_management.shoppingweb.dao.model.DeSell;
import com.project_management.shoppingweb.dao.model.User;
import com.project_management.shoppingweb.dao.vo.RequestResultVO;
import com.project_management.shoppingweb.repository.DeSellRepository;
import com.project_management.shoppingweb.repository.UserRepository;
import com.project_management.shoppingweb.service.DeSellService;
import com.project_management.shoppingweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeSellServiceImpl implements DeSellService {
    @Resource
    private DeSellRepository deSellRepository;
    @Autowired
    private UserService userService;
    @Resource
    private UserRepository userRepository;

    @Override
    public DeSell findBySellId(Integer sellId) {
        return deSellRepository.findByDeSellId(sellId);
    }

    @Override
    public List<DeSell> findByUserId(Integer userId) {
        return deSellRepository.findByUserId(userId);
    }

    @Override
    public List<DeSell> findAll() {
        return deSellRepository.findAll();
    }

    @Override
    public Page<DeSell> findAll(int start, int end) {
        return deSellRepository.findAll(new PageRequest(start,end));
    }

    @Override
    public RequestResultVO insert(DeSell deSell) {
        //保存用户名,创建时间
        User user = userRepository.findByUserId(deSell.getUserId());
        deSell.setUserName(user.getUsername());
        deSell.setSellTime(new Date());

        //存储，返回主键id
        deSellRepository.save(deSell);
        int deSellId = deSell.getDeSellId();


        Map map = new HashMap<>();
        RequestResultVO requestResultVO = new RequestResultVO();
        requestResultVO.setCode(HttpResponseConstants.Public.SUCCESS_CODE);
        requestResultVO.setMessage(HttpResponseConstants.Public.SUCCESS_200);
        map.put("sellId",deSellId);
        requestResultVO.setData(map);
        return requestResultVO;
    }

    @Override
    public RequestResultVO update(DeSell deSell) {
        DeSell oldDeSell = this.findBySellId(deSell.getDeSellId());
        if(deSell.getCredit()!=null){
            oldDeSell.setCredit(deSell.getCredit());
        }
        if(deSell.getInterest()!=null){
            oldDeSell.setInterest(deSell.getInterest());
        }
        if(deSell.getMoneyNum()!=null){
            oldDeSell.setMoneyNum(deSell.getMoneyNum());
        }
        if(deSell.getPeriod()!=null){
            oldDeSell.setPeriod(deSell.getPeriod());
        }
        if(deSell.getSellTime()!=null){
            oldDeSell.setSellTime(deSell.getSellTime());
        }
        deSellRepository.save(oldDeSell);

        RequestResultVO requestResultVO = new RequestResultVO();
        requestResultVO.setCode(HttpResponseConstants.Public.SUCCESS_CODE);
        requestResultVO.setMessage(HttpResponseConstants.Public.SUCCESS_300);
        return requestResultVO;
    }
}
