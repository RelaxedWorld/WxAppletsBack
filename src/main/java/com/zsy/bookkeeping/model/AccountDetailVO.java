package com.zsy.bookkeeping.model;

import lombok.Data;
import lombok.ToString;

/**
 * Created by Administrator on 2018/8/12.
 */
@Data
@ToString
public class AccountDetailVO {
    private Long id;
    private Integer accountTypeId;
    private String accountTypeName;
    private Integer positiveNegative;
    private Long amount;
    private String icon;
}
