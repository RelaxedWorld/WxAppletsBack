package com.zsy.bookkeeping.model;

import java.util.Date;

public class InOutDetailInfo extends InOutDetailInfoKey {
    private Integer inoutTypeId;

    private Long amount;

    private Long inoutAccountId;

    private String remark;

    private Date createTime;

    private Date modifyTime;

    public Integer getInoutTypeId() {
        return inoutTypeId;
    }

    public void setInoutTypeId(Integer inoutTypeId) {
        this.inoutTypeId = inoutTypeId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getInoutAccountId() {
        return inoutAccountId;
    }

    public void setInoutAccountId(Long inoutAccountId) {
        this.inoutAccountId = inoutAccountId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}