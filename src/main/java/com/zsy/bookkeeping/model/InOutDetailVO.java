package com.zsy.bookkeeping.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Created by Administrator on 2018/8/13.
 */
@Data
@ToString
public class InOutDetailVO {
    private Long id;
    private Integer inoutTypeId;
    private String inoutTypeDesc;
    private String inoutTypeIcon;
    private Long amount;
    private String remark;
    private Date createTime;
}
