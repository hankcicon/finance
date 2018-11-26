package com.project_management.shoppingweb.dao.interfaceValue;

import com.project_management.shoppingweb.dao.model.PageModel;

public class WorkflowPageModel extends PageModel{
    private String WorkflowType;


    public String getWorkflowType() {
        return WorkflowType;
    }

    public void setWorkflowType(String workflowType) {
        WorkflowType = workflowType;
    }
}
