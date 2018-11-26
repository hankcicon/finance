package com.project_management.shoppingweb.controller;

import com.project_management.shoppingweb.constant.HttpResponseConstants;
import com.project_management.shoppingweb.dao.interfaceValue.WorkflowPageModel;
import com.project_management.shoppingweb.dao.model.PageModel;
import com.project_management.shoppingweb.dao.model.Workflow;
import com.project_management.shoppingweb.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

import static com.project_management.shoppingweb.service.common.ResultBuilder.buildFailResult;
import static com.project_management.shoppingweb.service.common.ResultBuilder.buildSuccessResult;

@RestController
@RequestMapping("/workflow")
public class WorkflowController {
    @Autowired
    private WorkflowService workflowService;

    /**
     * 获取工作流列表
     * @param WorkflowPageModel
     * @return
     */
    @RequestMapping(value = "/findByPage.do", method = RequestMethod.POST)
    public @ResponseBody Object findByPage(@RequestBody WorkflowPageModel WorkflowPageModel) {
        Integer pageNow = WorkflowPageModel.getPageNow();
        Integer pageSize = WorkflowPageModel.getPageSize();
        if (pageNow == null || pageSize == null) return buildFailResult(HttpResponseConstants.Public.ERROR_800);
        Object workflowCustomPage = workflowService.findByPage(WorkflowPageModel);
        return buildSuccessResult(workflowCustomPage);
    }

    @RequestMapping(value = "/getWorkflowInfo.do",method = RequestMethod.POST)
    public @ResponseBody Object getWorkflowInfo(@RequestBody Workflow workflow) {
        return buildSuccessResult(workflowService.findBusinessInfo(workflow));
    }

    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    public @ResponseBody Object update(@RequestBody Workflow workflow){
        return buildSuccessResult(workflowService.findBusinessInfo(workflow));
    }

    @RequestMapping(value = "/test",method =RequestMethod.POST)
    public  @ResponseBody Object test() {
        Workflow workflow1=new Workflow();
        workflow1.setBusinessId(1);
        workflow1.setBusinessTable("DeBuy");
        //workflowService.handleBusinessTableStatus(workflow1);
        return buildSuccessResult();
    }

}
