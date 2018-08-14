package com.zsy.bookkeeping.interceptor;

import com.alibaba.fastjson.JSON;
import com.zsy.bookkeeping.model.Constants;
import com.zsy.bookkeeping.model.Result;
import com.zsy.bookkeeping.model.ResultEnum;
import com.zsy.bookkeeping.service.UserService;
import com.zsy.bookkeeping.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@Slf4j
public class BaseInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        response.setCharacterEncoding(Constants.UTF8);
        //登录和公开的不做拦截
        try {
            log.info("用户访问地址: {}, 来路地址: {}", request.getRequestURI(), CommonUtils.getIpAddrByRequest(request));
            String token = request.getRequestedSessionId();
            //token不存在
            if (StringUtils.isEmpty(token)) {
                returnJson(response, Result.getErrorResult(ResultEnum.LOGIN_TIME_EXP));
                return false;
            }
            //查看token是否存在,如果存在则刷新缓存时间
            Result result = userService.queryUserByToken(token);
            if (!result.isSuccess()) {
                returnJson(response, Result.getErrorResult(ResultEnum.LOGIN_TIME_EXP));
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            log.error("preHandle=" + e.getMessage());
        }
        return true;
    }

    private void returnJson(HttpServletResponse response, Result result) throws Exception {
        String json = JSON.toJSONString(result);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            log.error("response error", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}