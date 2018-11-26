package com.project_management.shoppingweb.dao.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="workflow")
public class Workflow{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="workflow_id")
    private Integer workflowId;//工作流id
    @Column(name="workflow_custom_id")
    private Integer workflowCustomId;//工作表id
    @Column(name="employee_id")
    private Integer employeeId;//用户id
    @Column(name="business_table")
    private String businessTable;//工作流表名
    @Column(name="business_id")
    private Integer businessId;//表id
    @Column(name="workflow_time")
    private Date workflowTime;//工作流创建时间
    @Column(name="change_time")
    private Date changeTime;//修改时间
    @Column(name="workflow_result")
    private String workflowResult;//审核结果
    @Column(name="workflow_opinion")
    private String workflowOpinion;//审核意见
    @Column(name="workflow_type")
    private String workflowType;//工作流类型
    @Column(name = "audit_user")
    private Integer auditUser;//审核人id

    public Integer getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(Integer auditUser) {
        this.auditUser = auditUser;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public Integer getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Integer workflowId) {
        this.workflowId = workflowId;
    }

    public Integer getWorkflowCustomId() {
        return workflowCustomId;
    }

    public void setWorkflowCustomId(Integer workflowCustomId) {
        this.workflowCustomId = workflowCustomId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getBusinessTable() {
        return businessTable;
    }

    public void setBusinessTable(String businessTable) {
        this.businessTable = businessTable;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Date getWorkflowTime() {
        return workflowTime;
    }

    public void setWorkflowTime(Date workflowTime) {
        this.workflowTime = workflowTime;
    }

    public String getWorkflowResult() {
        return workflowResult;
    }

    public void setWorkflowResult(String workflowResult) {
        this.workflowResult = workflowResult;
    }

    public String getWorkflowOpinion() {
        return workflowOpinion;
    }

    public void setWorkflowOpinion(String workflowOpinion) {
        this.workflowOpinion = workflowOpinion;
    }

    public String getWorkflowType() {
        return workflowType;
    }

    public void setWorkflowType(String workflowType) {
        this.workflowType = workflowType;
    }
}