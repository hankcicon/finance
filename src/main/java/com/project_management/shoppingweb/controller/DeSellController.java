package com.project_management.shoppingweb.controller;

import com.project_management.shoppingweb.dao.model.DeSell;
import com.project_management.shoppingweb.dao.model.PageModel;
import com.project_management.shoppingweb.dao.model.User;
import com.project_management.shoppingweb.dao.vo.RequestResultVO;
import com.project_management.shoppingweb.service.DeSellService;
import com.project_management.shoppingweb.service.common.ResultBuilder;
//import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/deSell")
public class DeSellController {
    @Autowired
    private DeSellService deSellService;

    /**
     * 查询某条具体待贷信息
     */
    @RequestMapping(value = "/getBySellId", method = RequestMethod.POST)
    public Object getBySellId(@RequestBody DeSell deSell1){
        int sellId = deSell1.getDeSellId();
        DeSell deSell = deSellService.findBySellId(sellId);
        return ResultBuilder.buildSuccessResult(deSell);
    }

    /**
     * 查询用户待贷信息
     */
    @RequestMapping(value = "/getByUserId", method = RequestMethod.POST)
    public Object getByUserId(@RequestBody User user){
        int userId = user.getUserId();
        List<DeSell> deSells = deSellService.findByUserId(userId);
        return ResultBuilder.buildSuccessResult(deSells);
    }

    /**
     * 查询所有待贷信息
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    public Object getAll(@RequestBody PageModel pageModel){
        RequestResultVO requestResultVO = ResultBuilder.buildSuccessResult();
        if(pageModel.getPageSize()!=null && pageModel.getPageNow()!=null){
            Page<DeSell> deSells = deSellService.findAll(pageModel.getPageNow()-1,pageModel.getPageSize());
            requestResultVO.setData(deSells);
        }else{
            List<DeSell> deSells = deSellService.findAll();
            requestResultVO.setData(deSells);
        }

        return requestResultVO;
    }

    /**
     * 新增待贷信息
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Object insert(@RequestBody DeSell deSell){
        return deSellService.insert(deSell);
    }

    /**
     * 修改待贷信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(@RequestBody DeSell deSell){
        return deSellService.update(deSell);
    }
}
