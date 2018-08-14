package com.zsy.bookkeeping.controller;

import com.zsy.bookkeeping.model.InOutDetailInfo;
import com.zsy.bookkeeping.model.Result;
import com.zsy.bookkeeping.model.ResultEnum;
import com.zsy.bookkeeping.service.InOutService;
import com.zsy.bookkeeping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping(value = "/bookkeeping/inOut")
public class InOutController {
    @Autowired
    private InOutService inOutService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/getInOutType")
    public Result getInOutType(@RequestParam("inOut") Integer inOut) {
        return Result.getSuccessResult(inOutService.getByInOutType(inOut));
    }

    @GetMapping(value = "/getInOutDetail")
    public Result getInOutDetail(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HttpServletRequest request) {
        Result result = userService.getRequestUserId(request);
        if (result.isSuccess()) {
            return Result.getSuccessResult(inOutService.getPageByUserId((String) result.getData(), pageNum, pageSize));
        } else {
            return result;
        }
    }

    @PostMapping(value = "/insertInOutDetail")
    public Result insertInOutDetail(InOutDetailInfo inOutDetailInfo, HttpServletRequest request) {
        Result result = userService.getRequestUserId(request);
        if (result.isSuccess()) {
            inOutDetailInfo.setCreateUserId(String.valueOf(result.getData()));
            inOutDetailInfo.setModifyTime(new Date());
            if (inOutService.insert(inOutDetailInfo)) {
                return Result.getSuccessResult(null);
            } else {
                return Result.getErrorResult(ResultEnum.INSERT_INOUT_DETAIL_ERROR);
            }
        } else {
            return result;
        }
    }

    @PostMapping(value = "/updateInOutDetail")
    public Result updateInOutDetail(@RequestParam("id") Long id, @RequestParam(value = "amount", required = false) Long amount, @RequestParam(value = "remark", required = false) String remark, @RequestParam(value = "inoutTypeId", required = false) Integer inoutTypeId) {
        InOutDetailInfo inOutDetailInfo = new InOutDetailInfo();
        inOutDetailInfo.setId(id);
        inOutDetailInfo.setAmount(amount);
        inOutDetailInfo.setRemark(remark);
        inOutDetailInfo.setInoutTypeId(inoutTypeId);
        inOutDetailInfo.setModifyTime(new Date());
        if (inOutService.update(inOutDetailInfo)) {
            return Result.getSuccessResult(null);
        } else {
            return Result.getErrorResult(ResultEnum.UPDATE_INOUT_DETAIL_ERROR);
        }
    }
}
