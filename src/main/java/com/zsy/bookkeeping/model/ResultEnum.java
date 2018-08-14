package com.zsy.bookkeeping.model;

public enum ResultEnum {
    SUCCESS(0,"success"),
    INSERT_INOUT_TYPE_ERROR(-1,"新增收支类型失败"),
    INSERT_INOUT_DETAIL_ERROR(-2,"新增收支明细失败"),
    INSERT_ACCOUNT_TYPE_ERROR(-3,"新增账户类型失败"),
    INSERT_ACCOUNT_DETAIL_ERROR(-4,"新增账户明细失败"),
    UPDATE_INOUT_TYPE_ERROR(-11,"修改收支类型失败"),
    UPDATE_INOUT_DETAIL_ERROR(-12,"修改收支明细失败"),
    UPDATE_ACCOUNT_TYPE_ERROR(-13,"修改账户类型失败"),
    UPDATE_ACCOUNT_DETAIL_ERROR(-14,"修改账户明细失败"),

    LOGIN_TIME_EXP(-100, "登录超时，请重新登录"),
    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
