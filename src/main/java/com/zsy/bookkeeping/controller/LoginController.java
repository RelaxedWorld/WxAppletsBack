package com.zsy.bookkeeping.controller;

import com.zsy.bookkeeping.model.Result;
import com.zsy.bookkeeping.model.UserInfo;
import com.zsy.bookkeeping.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping(value = "/bookkeeping")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public Result login(UserInfo userInfo, HttpServletRequest request, HttpServletResponse response) {
        return userService.userLogin(userInfo, request, response);
    }
}
