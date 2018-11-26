package com.project_management.shoppingweb.dao.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name="de_buy")
public class DeBuy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "de_buy_id")
    private Integer deBuyId;//贷款id
    @Column(name = "user_id")
    private Integer userId;//用户id
    @Column(name = "user_name")
    private String userName;//用户名
    @Column(name = "sell_time")
    private Date sellTime;//贷款时间
    @Column(name = "money_num")
    private BigInteger moneyNum;//交易数
    private  Integer period;//贷款周期
    private Double interest;//贷款利率
    private Integer credit;//征信级别
    private String status;//审核状态

    public Integer getDeBuyId() {
        return deBuyId;
    }

    public void setDeBuyId(Integer deBuyId) {
        this.deBuyId = deBuyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getSellTime() {
        return sellTime;
    }

    public void setSellTime(Date sellTime) {
        this.sellTime = sellTime;
    }

    public BigInteger getMoneyNum() {
        return moneyNum;
    }

    public void setMoneyNum(BigInteger moneyNum) {
        this.moneyNum = moneyNum;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}