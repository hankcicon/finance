package com.project_management.shoppingweb.dao.vo;

import com.project_management.shoppingweb.dao.model.Asset;
import com.project_management.shoppingweb.dao.model.Card;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class CreditVO {
    private Integer userId;//	用户id
    private Integer transationNum;//	交易数目
    private Integer delayTransation;//	逾期数目
    private Integer credit;//	征信级别
    private Date createTime;//	创建时间
    private String job;//职业
    private BigInteger annualIncome;//年收入
    private Double deposit;//	存款
    private List<Asset> assets;
    private List<Card> cards;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTransationNum() {
        return transationNum;
    }

    public void setTransationNum(Integer transationNum) {
        this.transationNum = transationNum;
    }

    public Integer getDelayTransation() {
        return delayTransation;
    }

    public void setDelayTransation(Integer delayTransation) {
        this.delayTransation = delayTransation;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public BigInteger getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(BigInteger annualIncome) {
        this.annualIncome = annualIncome;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
