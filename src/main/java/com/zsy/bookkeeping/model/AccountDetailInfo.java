package com.zsy.bookkeeping.model;

import java.util.Date;

public class AccountDetailInfo extends AccountDetailInfoKey {
    private Integer accountTypeId;

    private Integer positiveNegative;

    private Long amount;

    private Date createTime;

    private Date modifyTime;

    public Integer getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Integer accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public Integer getPositiveNegative() {
        return positiveNegative;
    }

    public void setPositiveNegative(Integer positiveNegative) {
        this.positiveNegative = positiveNegative;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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