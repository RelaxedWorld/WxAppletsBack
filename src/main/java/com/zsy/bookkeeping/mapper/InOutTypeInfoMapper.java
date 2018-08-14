package com.zsy.bookkeeping.mapper;

import com.zsy.bookkeeping.model.InOutTypeInfo;

import java.util.List;

public interface InOutTypeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InOutTypeInfo record);

    int insertSelective(InOutTypeInfo record);

    InOutTypeInfo selectByPrimaryKey(Integer id);

    List<InOutTypeInfo> selectSelective(InOutTypeInfo record);

    int updateByPrimaryKeySelective(InOutTypeInfo record);

    int updateByPrimaryKey(InOutTypeInfo record);
}