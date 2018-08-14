package com.zsy.bookkeeping.service.impl;

import com.zsy.bookkeeping.mapper.UserInfoMapper;
import com.zsy.bookkeeping.model.Constants;
import com.zsy.bookkeeping.model.Result;
import com.zsy.bookkeeping.model.ResultEnum;
import com.zsy.bookkeeping.model.UserInfo;
import com.zsy.bookkeeping.service.JedisService;
import com.zsy.bookkeeping.service.UserService;
import com.zsy.bookkeeping.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JedisService jedisService;
    @Value("${WX_USER_SESSION_KEY}")
    private String WX_USER_SESSION_KEY;
    @Value("${WX_SESSION_EXPIRE}")
    private Integer WX_SESSION_EXPIRE;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Result getRequestUserId(HttpServletRequest request) {
        String token = request.getRequestedSessionId();
        //token不存在
        if (StringUtils.isEmpty(token)) {
            return Result.getErrorResult(ResultEnum.LOGIN_TIME_EXP);
        }
        // 根据token从redis中查询用户信息
        String userId = jedisService.get(WX_USER_SESSION_KEY + ":" + token);
        // 判断是否为空
        if (StringUtils.isEmpty(userId)) {
            return Result.getErrorResult(ResultEnum.LOGIN_TIME_EXP);
        }
        return Result.getSuccessResult(userId);
    }

    @Override
    public boolean insertOrUpdate(UserInfo userInfo) {
        Integer row = userInfoMapper.insertOrUpdate(userInfo);
        if (row == 1) {
            return true;
        }
        return false;
    }

    @Override
    public UserInfo getByUserId(String userId) {
        return userInfoMapper.selectByPrimaryKey(userId);
    }

    @Override
    public Result userLogin(UserInfo userInfo, HttpServletRequest request, HttpServletResponse response) {
        Map sessionMap = CommonUtils.getSessionDataByJsCode(userInfo.getJsCode());
        if (sessionMap.get(Constants.SESSION_KEY) != null) {
            // 把用户信息写入 redis
            jedisService.set(WX_USER_SESSION_KEY + ":" + sessionMap.get(Constants.SESSION_KEY), String.valueOf(sessionMap.get(Constants.OPEN_ID)));
            // 设置 session 的过期时间
            jedisService.expire(WX_USER_SESSION_KEY + ":" + sessionMap.get(Constants.SESSION_KEY), WX_SESSION_EXPIRE);
            if (!StringUtils.isEmpty(userInfo.getNickName())) {
                userInfo.setUserId((String) sessionMap.get(Constants.OPEN_ID));
                userInfo.setCreateTime(new Date());
                userInfo.setModifyTime(new Date());
                //数据库中没有记录则新增，有则更新
                insertOrUpdate(userInfo);
            }
            sessionMap.remove(Constants.OPEN_ID);
            return Result.getSuccessResult(sessionMap);
        } else {
            return Result.getErrorResult(sessionMap.get(Constants.ERR_CODE), sessionMap.get(Constants.ERR_MSG));
        }
    }

    @Override
    public Result queryUserByToken(String token) {
        // 根据token从redis中查询用户信息
        String userId = jedisService.get(WX_USER_SESSION_KEY + ":" + token);
        // 判断是否为空
        if (StringUtils.isEmpty(userId)) {
            return Result.getErrorResult(ResultEnum.LOGIN_TIME_EXP);
        }
        // 更新过期时间
        jedisService.expire(WX_USER_SESSION_KEY + ":" + token, WX_SESSION_EXPIRE);

        // 返回用户信息
        return Result.getSuccessResult(userId);
    }
}
