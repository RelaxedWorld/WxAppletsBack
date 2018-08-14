package com.zsy.bookkeeping.mapper;

import com.zsy.bookkeeping.model.UserInfo;

import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    int insertOrUpdate(UserInfo record);

    UserInfo selectByPrimaryKey(String userId);

    List<UserInfo> selectSelective(UserInfo userInfo);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

}