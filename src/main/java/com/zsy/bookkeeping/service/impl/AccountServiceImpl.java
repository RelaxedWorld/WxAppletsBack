package com.zsy.bookkeeping.service.impl;

import com.github.pagehelper.PageHelper;
import com.zsy.bookkeeping.mapper.AccountDetailInfoMapper;
import com.zsy.bookkeeping.mapper.AccountTypeInfoMapper;
import com.zsy.bookkeeping.model.*;
import com.zsy.bookkeeping.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountTypeInfoMapper accountTypeInfoMapper;
    @Autowired
    private AccountDetailInfoMapper accountDetailInfoMapper;

    @Override
    public AccountTypeInfo getById(Integer id) {
        return accountTypeInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AccountTypeInfo> getByAssetType(Integer type) {
        AccountTypeInfo accountTypeInfo = new AccountTypeInfo();
        accountTypeInfo.setPositiveNegative(type);
        return accountTypeInfoMapper.selectSelective(accountTypeInfo);
    }

    @Override
    public boolean insert(AccountTypeInfo accountTypeInfo) {
        Integer row = accountTypeInfoMapper.insertSelective(accountTypeInfo);
        if (row == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(AccountTypeInfo accountTypeInfo) {
        Integer row = accountTypeInfoMapper.updateByPrimaryKeySelective(accountTypeInfo);
        if (row == 1) {
            return true;
        }
        return false;
    }

    @Override
    public AccountDetailInfo getById(AccountDetailInfoKey accountDetailInfoKey) {
        return accountDetailInfoMapper.selectByPrimaryKey(accountDetailInfoKey);
    }

    @Override
    public AccountDetailResponse getPageByUserId(String userId, int pageNum, int pageSize) {
        AccountDetailResponse accountDetailResponse = new AccountDetailResponse();
        //净资产金额
        Long realAssetAmount = 0L;
        //总资产金额
        Long assetAmount = 0L;
        //总负债金额
        Long liaAmount = 0L;
        List<AccountDetailVO> accountDetailVOList = new ArrayList<>();
        Map<Integer, AccountTypeInfo> accountMap = getAccountMap();
        AccountDetailInfo accountDetailInfo = new AccountDetailInfo();
        accountDetailInfo.setOwnUserId(userId);
        PageHelper.startPage(pageNum, pageSize);
        List<AccountDetailInfo> accountDetailInfoList = accountDetailInfoMapper.selectSelective(accountDetailInfo);
        for (AccountDetailInfo accountDetailInfo1 : accountDetailInfoList) {
            realAssetAmount += accountDetailInfo1.getAmount();
            if (accountDetailInfo1.getPositiveNegative() == 1) {
                //资产
                assetAmount += accountDetailInfo1.getAmount();
            } else {
                //负债
                liaAmount += accountDetailInfo1.getAmount();
            }
            AccountDetailVO accountDetailVO = new AccountDetailVO();
            BeanUtils.copyProperties(accountDetailInfo1, accountDetailVO);
            if (accountMap.get(accountDetailInfo1.getAccountTypeId()) != null) {
                AccountTypeInfo accountTypeInfo = accountMap.get(accountDetailInfo1.getAccountTypeId());
                accountDetailVO.setAccountTypeName(accountTypeInfo.getDescription());
                accountDetailVO.setIcon(accountTypeInfo.getIcon());
            }
            accountDetailVOList.add(accountDetailVO);
        }
        accountDetailResponse.setRealAssetAmount(realAssetAmount);
        accountDetailResponse.setAssetAmount(assetAmount);
        accountDetailResponse.setLiaAmount(liaAmount);
        accountDetailResponse.setAccountDetailVOList(accountDetailVOList);
        return accountDetailResponse;
    }

    public Map<Integer, AccountTypeInfo> getAccountMap() {
        Map<Integer, AccountTypeInfo> accountTypeInfoMap = new HashMap<>();
        List<AccountTypeInfo> accountTypeInfoList = accountTypeInfoMapper.selectSelective(new AccountTypeInfo());
        for (AccountTypeInfo accountTypeInfo : accountTypeInfoList) {
            accountTypeInfoMap.put(accountTypeInfo.getId(), accountTypeInfo);
        }
        return accountTypeInfoMap;
    }

    @Override
    public List<AccountDetailInfo> getPageByUserIdAssetType(String userId, Integer type, int pageNum, int pageSize) {
        AccountDetailInfo accountDetailInfo = new AccountDetailInfo();
        accountDetailInfo.setOwnUserId(userId);
        accountDetailInfo.setPositiveNegative(type);
        PageHelper.startPage(pageNum, pageSize);
        return accountDetailInfoMapper.selectSelective(accountDetailInfo);
    }

    @Override
    public boolean insert(AccountDetailInfo accountDetailInfo) {
        Integer row = accountDetailInfoMapper.insertSelective(accountDetailInfo);
        if (row == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(AccountDetailInfo accountDetailInfo) {
        Integer row = accountDetailInfoMapper.updateByPrimaryKeySelective(accountDetailInfo);
        if (row == 1) {
            return true;
        }
        return false;
    }
}
