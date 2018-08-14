package com.zsy.bookkeeping.service;

import com.zsy.bookkeeping.model.Result;
import com.zsy.bookkeeping.model.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    Result getRequestUserId(HttpServletRequest request);

    boolean insertOrUpdate(UserInfo userInfo);

    UserInfo getByUserId(String userId);

    Result userLogin(UserInfo userInfo, HttpServletRequest request, HttpServletResponse response);

    Result queryUserByToken(String token);
}
