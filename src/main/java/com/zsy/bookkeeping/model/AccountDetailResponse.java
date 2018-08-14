package com.zsy.bookkeeping.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by Administrator on 2018/8/12.
 */
@Data
@ToString
public class AccountDetailResponse {
    //净资产金额
    private Long realAssetAmount;
    //总资产金额
    private Long assetAmount;
    //总负债金额
    private Long liaAmount;
    //资产列表
    private List<AccountDetailVO> accountDetailVOList;
}
