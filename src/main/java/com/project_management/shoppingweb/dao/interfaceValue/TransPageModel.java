package com.project_management.shoppingweb.dao.interfaceValue;

import com.project_management.shoppingweb.dao.model.PageModel;
import com.project_management.shoppingweb.dao.model.Transaction;

import java.util.Date;

public class TransPageModel extends Transaction {
    private Integer pageSize;
    private Integer pageNow;
    private Date startTime;
    private Date endTime;
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }
}
