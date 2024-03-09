package com.weiqt.app.entity;

import com.weiqt.app.database.util.Identifiable;

import java.io.Serializable;
import java.util.Date;

public class Record implements Identifiable, Serializable {
    private Integer id;
    private Integer userId;
    private Integer op; // 0 表示消费，1表示充值
    private Double amount; // 交易金额

    public Record(Integer id, Integer userId, Integer op, Double amount) {
        this.id = id;
        this.userId = userId;
        this.op = op;
        this.amount = amount;

        isDelete = false;
        createTime = new Date();
        updateTime = createTime;
        deleteTime = null;
    }

    private Boolean isDelete;
    private Date createTime;
    private Date updateTime;
    private Date deleteTime;

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

    @Override
    public Integer getId() {
        return null;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getOp() {
        return op;
    }

    public Double getAmount() {
        return amount;
    }
}

