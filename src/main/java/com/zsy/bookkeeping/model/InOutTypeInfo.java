package com.zsy.bookkeeping.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
@Data
@ToString
public class InOutTypeInfo {
    private Integer id;

    private String description;

    private String icon;

    private Integer inOut;

    private Date createTime;

    private Date modifyTime;

}