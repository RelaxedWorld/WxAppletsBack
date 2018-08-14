package com.zsy.bookkeeping.controller;

import com.zsy.bookkeeping.model.AccountDetailInfo;
import com.zsy.bookkeeping.model.Result;
import com.zsy.bookkeeping.model.ResultEnum;
import com.zsy.bookkeeping.service.AccountService;
import com.zsy.bookkeeping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping(value = "/bookkeeping/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/getAccountType")
    public Result getAccountType(@RequestParam(value = "assetType", required = false) Integer assetType) {
        return Result.getSuccessResult(accountService.getByAssetType(assetType));
    }

    @GetMapping(value = "/getAccountDetail")
    public Result getAccountDetail(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HttpServletRequest request) {
        Result result = userService.getRequestUserId(request);
        if (result.isSuccess()) {
            return Result.getSuccessResult(accountService.getPageByUserId((String) result.getData(), pageNum, pageSize));
        } else {
            return result;
        }
    }

    @PostMapping(value = "/insertAccountDetail")
    public Result insertAccountDetail(AccountDetailInfo accountDetailInfo, HttpServletRequest request) {
        Result result = userService.getRequestUserId(request);
        if (result.isSuccess()) {
            accountDetailInfo.setOwnUserId(String.valueOf(result.getData()));
            accountDetailInfo.setCreateTime(new Date());
            accountDetailInfo.setModifyTime(new Date());
            if (accountService.insert(accountDetailInfo)) {
                return Result.getSuccessResult(null);
            } else {
                return Result.getErrorResult(ResultEnum.INSERT_ACCOUNT_DETAIL_ERROR);
            }
        } else {
            return result;
        }
    }

    @PostMapping(value = "/updateAccountDetail")
    public Result updateAccountDetail(@RequestParam("id") Long id, @RequestParam(value = "amount", required = false) Long amount, @RequestParam(value = "accountTypeId", required = false) Integer accountTypeId, @RequestParam(value = "assetType", required = false) Integer assetType) {
        AccountDetailInfo accountDetailInfo = new AccountDetailInfo();
        accountDetailInfo.setId(id);
        accountDetailInfo.setAmount(amount);
        accountDetailInfo.setAccountTypeId(accountTypeId);
        accountDetailInfo.setPositiveNegative(assetType);
        accountDetailInfo.setModifyTime(new Date());
        if (accountService.update(accountDetailInfo)) {
            return Result.getSuccessResult(null);
        } else {
            return Result.getErrorResult(ResultEnum.UPDATE_ACCOUNT_DETAIL_ERROR);
        }
    }
}
