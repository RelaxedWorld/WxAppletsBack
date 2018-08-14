package com.zsy.bookkeeping.controller;

import com.zsy.bookkeeping.model.*;
import com.zsy.bookkeeping.service.AccountService;
import com.zsy.bookkeeping.service.InOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/bookkeeping/admin")
public class AdminController {
    @Autowired
    private InOutService inOutService;
    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/insertInOutType")
    public Result insertInOutType(@RequestParam("description") String description, @RequestParam("icon") String icon, @RequestParam("inOut") Integer inOut) {
        InOutTypeInfo inOutTypeInfo = new InOutTypeInfo();
        inOutTypeInfo.setDescription(description);
        inOutTypeInfo.setIcon(icon);
        inOutTypeInfo.setInOut(inOut);
        inOutTypeInfo.setCreateTime(new Date());
        inOutTypeInfo.setModifyTime(new Date());
        if (inOutService.insert(inOutTypeInfo)) {
            return Result.getSuccessResult(null);
        } else {
            return Result.getErrorResult(ResultEnum.INSERT_INOUT_TYPE_ERROR);
        }
    }

    @PostMapping(value = "/updateInOutType")
    public Result updateInOutType(@RequestParam("id") Integer id, @RequestParam(value = "description", required = false) String description, @RequestParam(value = "icon", required = false) String icon, @RequestParam(value = "inOut", required = false) Integer inOut) {
        InOutTypeInfo inOutTypeInfo = new InOutTypeInfo();
        inOutTypeInfo.setId(id);
        inOutTypeInfo.setDescription(description);
        inOutTypeInfo.setIcon(icon);
        inOutTypeInfo.setInOut(inOut);
        inOutTypeInfo.setModifyTime(new Date());
        if (inOutService.update(inOutTypeInfo)) {
            return Result.getSuccessResult(null);
        } else {
            return Result.getErrorResult(ResultEnum.UPDATE_INOUT_TYPE_ERROR);
        }
    }

    @PostMapping(value = "/insertAccountType")
    public Result insertAccountType(AccountTypeInfo accountTypeInfo) {
        accountTypeInfo.setCreateTime(new Date());
        accountTypeInfo.setModifyTime(new Date());
        if (accountService.insert(accountTypeInfo)) {
            return Result.getSuccessResult(null);
        } else {
            return Result.getErrorResult(ResultEnum.INSERT_ACCOUNT_TYPE_ERROR);
        }
    }

    @PostMapping(value = "/updateAccountType")
    public Result updateAccountType(AccountTypeInfo accountTypeInfo) {
        accountTypeInfo.setId(null);
        accountTypeInfo.setModifyTime(new Date());
        if (accountService.update(accountTypeInfo)) {
            return Result.getSuccessResult(null);
        } else {
            return Result.getErrorResult(ResultEnum.UPDATE_ACCOUNT_TYPE_ERROR);
        }
    }

}
