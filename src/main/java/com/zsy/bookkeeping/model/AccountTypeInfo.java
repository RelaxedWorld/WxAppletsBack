package com.zsy.bookkeeping.model;

import java.util.Date;

public class AccountTypeInfo {
    private Integer id;

    private String description;

    private String icon;

    private Integer positiveNegative;

    private Date createTime;

    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getPositiveNegative() {
        return positiveNegative;
    }

    public void setPositiveNegative(Integer positiveNegative) {
        this.positiveNegative = positiveNegative;
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