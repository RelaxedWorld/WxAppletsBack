package com.zsy.bookkeeping.model;

public class AccountDetailInfoKey {
    private Long id;

    private String ownUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnUserId() {
        return ownUserId;
    }

    public void setOwnUserId(String ownUserId) {
        this.ownUserId = ownUserId == null ? null : ownUserId.trim();
    }
}