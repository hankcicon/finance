package com.project_management.shoppingweb.dao.vo;

import com.project_management.shoppingweb.dao.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class UserVO {
    public class User implements UserDetails {

        private Integer userId;//	用户id
        private String username;//	用户名
        private  String password;//	密码
        private List<Role> roles;
        private String signature;//	签名
        private Date pwdModifyDate;//	密码修改时间
        private String pwdModifyTime;
        private Date pwdEfficitiveDay;//	密码激活时间
        private Date pwdEfficitiveTime;
        private Integer status;//	状态
        private Integer type;//	类型
        private String description;//	描述
        private Integer creator;//	创建者
        private Date createTime;//	创建时间
        private Integer isDeleted;//	是否在用
        private String email;//	邮箱
        private String card;//	卡号
        private Double deposit;//	存款
        private Integer transationNum;//	交易数目
        private Integer delayTransation;//	逾期数目
        private Integer credit;//	征信级别
        private Integer gender;//性别
        private Date birthDate;//出生日期
        private String job;//职业
        private BigInteger annualIncome;//年收入
        private String idType;//证件类型
        private String idNum;//证件号
        private String phoneNumber;
        private Date amendTime;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }



        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        @Override
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            List<Role> roles = this.getRoles();
            for(Role role : roles){
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
            return authorities;
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

        public Date getPwdModifyDate() {
            return pwdModifyDate;
        }

        public void setPwdModifyDate(Date pwdModifyDate) {
            this.pwdModifyDate = pwdModifyDate;
        }

        public Date getPwdEfficitiveDay() {
            return pwdEfficitiveDay;
        }

        public void setPwdEfficitiveDay(Date pwdEfficitiveDay) {
            this.pwdEfficitiveDay = pwdEfficitiveDay;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
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

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public Integer getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(Integer isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCard() {
            return card;
        }

        public void setCard(String card) {
            this.card = card;
        }

        public Double getDeposit() {
            return deposit;
        }

        public void setDeposit(Double deposit) {
            this.deposit = deposit;
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

        public List<Role> getRoles() {
            return roles;
        }

        public void setRoles(List<Role> roles) {
            this.roles = roles;
        }

        public Integer getGender() {
            return gender;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }

        public Date getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
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

        public String getIdType() {
            return idType;
        }

        public void setIdType(String idType) {
            this.idType = idType;
        }

        public String getIdNum() {
            return idNum;
        }

        public void setIdNum(String idNum) {
            this.idNum = idNum;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Date getAmendTime() {
            return amendTime;
        }

        public void setAmendTime(Date amendTime) {
            this.amendTime = amendTime;
        }
    }

}
