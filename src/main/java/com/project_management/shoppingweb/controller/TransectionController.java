package com.project_management.shoppingweb.controller;

import com.project_management.shoppingweb.constant.HttpResponseConstants;
import com.project_management.shoppingweb.dao.interfaceValue.StatisticsModel;
import com.project_management.shoppingweb.dao.interfaceValue.TransPageModel;
import com.project_management.shoppingweb.dao.interfaceValue.WorkflowPageModel;
import com.project_management.shoppingweb.dao.model.PageModel;
import com.project_management.shoppingweb.dao.model.Transaction;
import com.project_management.shoppingweb.dao.model.Workflow;
import com.project_management.shoppingweb.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.project_management.shoppingweb.service.common.ResultBuilder.buildFailResult;
import static com.project_management.shoppingweb.service.common.ResultBuilder.buildSuccessResult;

@RestController
@RequestMapping("/transaction")
public class TransectionController {
    @Autowired
    private TransactionService transactionService;
    @RequestMapping(value = "/getByPage.do", method = RequestMethod.POST)
    public @ResponseBody
    Object getByPage(@RequestBody TransPageModel transPageModel) {
        Integer pageNow = transPageModel.getPageNow();
        Integer pageSize = transPageModel.getPageSize();
        if (pageNow == null || pageSize == null) return buildFailResult(HttpResponseConstants.Public.ERROR_800);
        transPageModel.setPageNow(transPageModel.getPageNow()-1);
        Object workflowCustomPage = transactionService.getByPage(transPageModel);
        return buildSuccessResult(workflowCustomPage);
    }
    @RequestMapping(value = "/getBuyByPage.do", method = RequestMethod.POST)
    public @ResponseBody
    Object getBuyByPage(@RequestBody TransPageModel transPageModel) {
        Integer pageNow = transPageModel.getPageNow();
        Integer pageSize = transPageModel.getPageSize();
        if (pageNow == null || pageSize == null) return buildFailResult(HttpResponseConstants.Public.ERROR_800);
        transPageModel.setPageNow(transPageModel.getPageNow()-1);
        Object workflowCustomPage = transactionService.getBuyByPage(transPageModel);
        return buildSuccessResult(workflowCustomPage);
    }
    @RequestMapping(value = "/getSellByPage.do", method = RequestMethod.POST)
    public @ResponseBody
    Object getSellByPage(@RequestBody TransPageModel transPageModel) {
        Integer pageNow = transPageModel.getPageNow();
        Integer pageSize = transPageModel.getPageSize();
        if (pageNow == null || pageSize == null) return buildFailResult(HttpResponseConstants.Public.ERROR_800);
        transPageModel.setPageNow(transPageModel.getPageNow()-1);
        Object workflowCustomPage = transactionService.getSellByPage(transPageModel);
        return buildSuccessResult(workflowCustomPage);
    }
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    public @ResponseBody Object update(@RequestBody Transaction transaction){
        return buildSuccessResult(transactionService.update(transaction));
    }
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    public @ResponseBody Object delete(@RequestBody Transaction transaction){
        transactionService.delete(transaction);
        return buildSuccessResult();
    }
    @RequestMapping(value = "/insert.do",method = RequestMethod.POST)
    public @ResponseBody Object insert(@RequestBody Transaction transaction){
        return buildSuccessResult(transactionService.insert(transaction));
    }

    @RequestMapping(value = "/getStatistic",method = RequestMethod.POST)
    public @ResponseBody Object getStatistic(@RequestBody StatisticsModel statisticsModel){
        return buildSuccessResult(transactionService.getStatistics(statisticsModel));
    }
    @RequestMapping(value = "/selectOne.do",method = RequestMethod.POST)
    public @ResponseBody Object selcetOne(@RequestBody TransPageModel transPageModel){
        transPageModel.setSellStatus("O");
        transPageModel.setBuyStatus("O");
        transPageModel.setSellId(transPageModel.getUserId());
        transPageModel.setBuyId(transPageModel.getUserId());
        transPageModel.setPageNow(transPageModel.getPageNow()-1);
        return buildSuccessResult(transactionService.getByPage1(transPageModel));
    }
    @RequestMapping(value = "/selectTwo.do",method = RequestMethod.POST)
    public @ResponseBody Object selcetTwo(@RequestBody TransPageModel transPageModel){
        transPageModel.setSellStatus("Y");
        transPageModel.setBuyStatus("Y");
        transPageModel.setSellId(transPageModel.getUserId());
        transPageModel.setPageNow(transPageModel.getPageNow()-1);
        return buildSuccessResult(transactionService.getByPage(transPageModel));
    }
    @RequestMapping(value = "/selectThree.do",method = RequestMethod.POST)
    public @ResponseBody Object selcetThree(@RequestBody TransPageModel transPageModel){
        if(transPageModel.getUserId()!=null)
        transPageModel.setSellId(transPageModel.getUserId());
        transPageModel.setPageNow(transPageModel.getPageNow()-1);
        return buildSuccessResult(transactionService.getByPageAd(transPageModel));
    }
    @RequestMapping(value = "/selectFour.do",method = RequestMethod.POST)
    public @ResponseBody Object selcetFour(@RequestBody TransPageModel transPageModel){
        if(transPageModel.getUserId()!=null)
        transPageModel.setBuyId(transPageModel.getUserId());
        transPageModel.setPageNow(transPageModel.getPageNow()-1);
        return buildSuccessResult(transactionService.getByPageAd(transPageModel));
    }
}
