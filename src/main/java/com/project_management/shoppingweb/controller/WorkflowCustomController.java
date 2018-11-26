package com.project_management.shoppingweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.project_management.shoppingweb.constant.HttpResponseConstants;
import com.project_management.shoppingweb.dao.model.PageModel;
import com.project_management.shoppingweb.dao.model.Workflow;
import com.project_management.shoppingweb.dao.model.WorkflowCustom;
import com.project_management.shoppingweb.service.WorkflowCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.project_management.shoppingweb.service.common.ResultBuilder.buildFailResult;
import static com.project_management.shoppingweb.service.common.ResultBuilder.buildSuccessResult;

@RestController
@RequestMapping("/workflowCustom")
public class WorkflowCustomController {
    @Autowired
    private WorkflowCustomService workflowCustomService;

    /**
     * 获取工作流列表
     * @param pageModel
     * @return
     */
    @RequestMapping(value = "/findByPage.do", method = RequestMethod.POST)
    public @ResponseBody Object findByPage(@RequestBody PageModel pageModel) {
        Integer pageNow = pageModel.getPageNow();
        Integer pageSize = pageModel.getPageSize();
        if (pageNow == null || pageSize == null) return buildFailResult(HttpResponseConstants.Public.ERROR_800);
        Object workflowCustomPage = workflowCustomService.findByPage(pageNow, pageSize);
        return buildSuccessResult(workflowCustomPage);
    }

    /**
     * 插入工作流
     * @param workflowCustom
     * @return
     */
    @RequestMapping(value = "/insert.do", method = RequestMethod.POST)
    public @ResponseBody Object insert(@RequestBody WorkflowCustom workflowCustom){
        WorkflowCustom res=workflowCustomService.insert(workflowCustom);
        return buildSuccessResult(res);
    }

    /**
     * 更新工作流
     * @param workflowCustom
     * @return
     */
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public @ResponseBody Object update(@RequestBody WorkflowCustom workflowCustom){
        WorkflowCustom res=workflowCustomService.insert(workflowCustom);
        return buildSuccessResult(res);
    }

    /**
     * 删除工作流
     * @param workflowCustom
     * @return
     */
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    public @ResponseBody Object delete(@RequestBody WorkflowCustom workflowCustom){
        workflowCustomService.delete(workflowCustom);
        return buildSuccessResult();
    }
}
