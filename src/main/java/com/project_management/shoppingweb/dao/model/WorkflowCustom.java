package com.project_management.shoppingweb.dao.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name="workflow_custom")
public class WorkflowCustom{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="workflow_custom_id")
    private Integer workflowCustomId;//工作流表id
    @Column(name="name")
    private String name;//工作流名称
    @Column(name="business_table")
    private String businessTable;//工作流表
    @Column(name="workflow_custom")
    private Integer workflowCustom;//工作流过程(审核人)

    public Integer getWorkflowCustomId() {
        return workflowCustomId;
    }

    public void setWorkflowCustomId(Integer workflowCustomId) {
        this.workflowCustomId = workflowCustomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessTable() {
        return businessTable;
    }

    public void setBusinessTable(String businessTable) {
        this.businessTable = businessTable;
    }

    public Integer getWorkflowCustom() {
        return workflowCustom;
    }

    public void setWorkflowCustom(Integer workflowCustom) {
        this.workflowCustom = workflowCustom;
    }
}