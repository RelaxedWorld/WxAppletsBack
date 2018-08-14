package com.zsy.bookkeeping.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
@Data
@ToString
public class UserInfo {
    private String jsCode;

    private String userId;

    private String nickName;

    private Integer gender;

    private Integer userLevel;

    private String country;

    private String province;

    private String city;

    private Date createTime;

    private Date modifyTime;

}