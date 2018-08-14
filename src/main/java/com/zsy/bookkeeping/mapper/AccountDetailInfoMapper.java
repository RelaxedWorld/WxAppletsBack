package com.zsy.bookkeeping.mapper;

import com.zsy.bookkeeping.model.AccountDetailInfo;
import com.zsy.bookkeeping.model.AccountDetailInfoKey;

import java.util.List;

public interface AccountDetailInfoMapper {
    int deleteByPrimaryKey(AccountDetailInfoKey key);

    int insert(AccountDetailInfo record);

    int insertSelective(AccountDetailInfo record);

    AccountDetailInfo selectByPrimaryKey(AccountDetailInfoKey key);

    List<AccountDetailInfo> selectSelective(AccountDetailInfo record);

    int updateByPrimaryKeySelective(AccountDetailInfo record);

    int updateByPrimaryKey(AccountDetailInfo record);
}