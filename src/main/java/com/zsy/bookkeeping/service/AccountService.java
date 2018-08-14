package com.zsy.bookkeeping.service;

import com.zsy.bookkeeping.model.AccountDetailInfo;
import com.zsy.bookkeeping.model.AccountDetailInfoKey;
import com.zsy.bookkeeping.model.AccountDetailResponse;
import com.zsy.bookkeeping.model.AccountTypeInfo;

import java.util.List;

public interface AccountService {
    AccountTypeInfo getById(Integer id);

    List<AccountTypeInfo> getByAssetType(Integer type);

    boolean insert(AccountTypeInfo accountTypeInfo);

    boolean update(AccountTypeInfo accountTypeInfo);

    AccountDetailInfo getById(AccountDetailInfoKey accountDetailInfoKey);

    AccountDetailResponse getPageByUserId(String userId, int pageNum, int pageSize);

    List<AccountDetailInfo> getPageByUserIdAssetType(String userId, Integer type, int pageNum, int pageSize);

    boolean insert(AccountDetailInfo accountDetailInfo);

    boolean update(AccountDetailInfo accountDetailInfo);
}
