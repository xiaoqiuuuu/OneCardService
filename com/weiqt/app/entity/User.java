package com.weiqt.app.entity;

import com.weiqt.app.database.util.Identifiable;

import java.io.Serializable;
import java.util.Date;

public class User implements Identifiable, Serializable {
    private Integer id;
    private String name; // 用户名
    private String password; // 登录密码
    private String card; // 卡号
    private String cardPassword; // 消费密码
    private Double balance; // 余额
    private String phone; // 联系电话

    private Boolean isDelete;
    private Date createTime;
    private Date updateTime;
    private Date deleteTime;

    public User(Integer id, String name, String password, String card, String cardPassword) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.card = card;
        this.cardPassword = cardPassword;

        createTime = new Date();
        updateTime = createTime;
        deleteTime = null;
        isDelete = false;
        balance = 0D;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", card='" + card + '\'' +
                ", cardPassword='" + cardPassword + '\'' +
                ", balance=" + balance +
                ", phone='" + phone + '\'' +
                ", isDelete=" + isDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleteTime=" + deleteTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCardPassword() {
        return cardPassword;
    }

    public void setCardPassword(String cardPassword) {
        this.cardPassword = cardPassword;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}
