package com.zsy.bookkeeping.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Result {
    private boolean success;
    private Integer code;
    private String msg;
    private Object data;

    public static Result getSuccessResult(Object data) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    public static Result getErrorResult(ResultEnum resultEnum) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        result.setData(null);
        return result;
    }

    public static Result getErrorResult(Object code, Object msg) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode((Integer) code);
        result.setMsg((String) msg);
        result.setData(null);
        return result;
    }
}
