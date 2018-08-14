package com.zsy.bookkeeping.mapper;

import com.zsy.bookkeeping.model.InOutDetailInfo;
import com.zsy.bookkeeping.model.InOutDetailInfoKey;

import java.util.List;

public interface InOutDetailInfoMapper {
    int deleteByPrimaryKey(InOutDetailInfoKey key);

    int insert(InOutDetailInfo record);

    int insertSelective(InOutDetailInfo record);

    InOutDetailInfo selectByPrimaryKey(InOutDetailInfoKey key);

    List<InOutDetailInfo> selectSelective(InOutDetailInfo record);

    int updateByPrimaryKeySelective(InOutDetailInfo record);

    int updateByPrimaryKey(InOutDetailInfo record);
}