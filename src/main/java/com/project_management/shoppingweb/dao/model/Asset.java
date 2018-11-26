package com.project_management.shoppingweb.dao.model;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name="asset")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="asset_id")
    private Integer assetId;//凭证id
    @Column(name="asset_type")
    private String assetType;//凭证类型
    @Column(name="user_id")
    private Integer userId;
    @Column(name="assessmen_value")
    private BigInteger assessmenValue;//估值
    @Column(name="assess_certification")
    private String assessCertification;//证明

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigInteger getAssessmenValue() {
        return assessmenValue;
    }

    public void setAssessmenValue(BigInteger assessmenValue) {
        this.assessmenValue = assessmenValue;
    }

    public String getAssessCertification() {
        return assessCertification;
    }

    public void setAssessCertification(String assessCertification) {
        this.assessCertification = assessCertification;
    }
}
