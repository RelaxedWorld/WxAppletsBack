package com.zsy.bookkeeping.mapper;

import com.zsy.bookkeeping.model.AccountTypeInfo;

import java.util.List;

public interface AccountTypeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountTypeInfo record);

    int insertSelective(AccountTypeInfo record);

    AccountTypeInfo selectByPrimaryKey(Integer id);

    List<AccountTypeInfo> selectSelective(AccountTypeInfo record);

    int updateByPrimaryKeySelective(AccountTypeInfo record);

    int updateByPrimaryKey(AccountTypeInfo record);
}