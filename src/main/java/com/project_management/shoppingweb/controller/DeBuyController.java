package com.project_management.shoppingweb.controller;

import com.project_management.shoppingweb.dao.model.DeBuy;
import com.project_management.shoppingweb.dao.model.DeSell;
import com.project_management.shoppingweb.dao.model.PageModel;
import com.project_management.shoppingweb.dao.model.User;
import com.project_management.shoppingweb.dao.vo.RequestResultVO;
import com.project_management.shoppingweb.service.DeBuyService;
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
@RequestMapping("/deBuy")
public class DeBuyController {
    @Autowired
    private DeBuyService deBuyService;

    /**
     * 查询某条具体待借信息
     */
    @RequestMapping(value = "/getByBuyId", method = RequestMethod.POST)
    public Object getByBuyId(@RequestBody DeBuy deBuy1){
        int buyId = deBuy1.getDeBuyId();
        DeBuy deBuy = deBuyService.findByBuyId(buyId);
        return ResultBuilder.buildSuccessResult(deBuy);
    }

    /**
     * 查询用户待借信息
     */
    @RequestMapping(value = "/getByUserId", method = RequestMethod.POST)
    public Object getByUserId(@RequestBody User user){
        int userId = user.getUserId();
        List<DeBuy> deBuys = deBuyService.findByUserId(userId);
        return ResultBuilder.buildSuccessResult(deBuys);
    }

    /**
     * 查询所有待借信息
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    public Object getAll(@RequestBody PageModel pageModel){
        RequestResultVO requestResultVO = ResultBuilder.buildSuccessResult();
        //传入分页参数则调用分页接口
        if(pageModel.getPageNow()!=null && pageModel.getPageSize()!=null){
            Page<DeBuy> deBuys = deBuyService.findAll(pageModel.getPageNow()-1,pageModel.getPageSize());
            requestResultVO.setData(deBuys);
        }else{  //否则调用普通接口
            List<DeBuy> deBuys = deBuyService.findAll();
            requestResultVO.setData(deBuys);
        }

        return requestResultVO;
    }

    /**
     * 新增待借信息
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Object insert(@RequestBody DeBuy deBuy){
        return deBuyService.insert(deBuy);
    }

    /**
     * 修改待借信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(@RequestBody DeBuy deBuy){
        return deBuyService.update(deBuy);
    }
}
