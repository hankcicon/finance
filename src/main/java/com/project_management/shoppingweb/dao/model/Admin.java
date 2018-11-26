package com.project_management.shoppingweb.dao.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="adminster_id")
    private Integer administerId;//管理员id
    @Column(name="administer_name")
    private String  administerName;//管理员姓名
    private String  password;//密码
    private String  signature;//签名
    private Integer type;//类型
    private String description;//描述
    private Integer creator;//创建者
    private Date create_time;//创建时间
    private Integer is_deleted;//是否使用
    private String email;//邮箱

    public Integer getAdministerId() {
        return administerId;
    }

    public void setAdministerId(Integer administerId) {
        this.administerId = administerId;
    }

    public String getAdministerName() {
        return administerName;
    }

    public void setAdministerName(String administerName) {
        this.administerName = administerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Integer is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
