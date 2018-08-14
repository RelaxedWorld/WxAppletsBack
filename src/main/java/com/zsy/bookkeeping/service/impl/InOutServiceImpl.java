package com.zsy.bookkeeping.service.impl;

import com.github.pagehelper.PageHelper;
import com.zsy.bookkeeping.mapper.InOutDetailInfoMapper;
import com.zsy.bookkeeping.mapper.InOutTypeInfoMapper;
import com.zsy.bookkeeping.model.InOutDetailInfo;
import com.zsy.bookkeeping.model.InOutDetailVO;
import com.zsy.bookkeeping.model.InOutResponse;
import com.zsy.bookkeeping.model.InOutTypeInfo;
import com.zsy.bookkeeping.service.InOutService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InOutServiceImpl implements InOutService {
    @Autowired
    private InOutTypeInfoMapper inOutTypeInfoMapper;
    @Autowired
    private InOutDetailInfoMapper inOutDetailInfoMapper;

    @Override
    public List<InOutTypeInfo> getAll() {
        InOutTypeInfo inOutTypeInfo = new InOutTypeInfo();
        return inOutTypeInfoMapper.selectSelective(inOutTypeInfo);
    }

    @Override
    public List<InOutTypeInfo> getByInOutType(Integer inOut) {
        InOutTypeInfo inOutTypeInfo = new InOutTypeInfo();
        inOutTypeInfo.setInOut(inOut);
        return inOutTypeInfoMapper.selectSelective(inOutTypeInfo);
    }

    @Override
    public InOutTypeInfo getById(Integer id) {
        return inOutTypeInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean insert(InOutTypeInfo inOutTypeInfo) {
        Integer row = inOutTypeInfoMapper.insertSelective(inOutTypeInfo);
        if (row > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(InOutTypeInfo inOutTypeInfo) {
        Assert.notNull(inOutTypeInfo.getId(), "收支类型id不能为空");
        Integer row = inOutTypeInfoMapper.updateByPrimaryKeySelective(inOutTypeInfo);
        if (row == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean insert(InOutDetailInfo inOutDetailInfo) {
        Integer row = inOutDetailInfoMapper.insertSelective(inOutDetailInfo);
        if (row > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(InOutDetailInfo inOutDetailInfo) {
        Integer row = inOutDetailInfoMapper.updateByPrimaryKeySelective(inOutDetailInfo);
        if (row == 1) {
            return true;
        }
        return false;
    }

    @Override
    public InOutResponse getPageByUserId(String userId, int pageNum, int pageSize) {
        InOutResponse inOutResponse = new InOutResponse();
        List<InOutDetailVO> inOutDetailVOList = new ArrayList<>();
        InOutDetailInfo inOutDetailInfo = new InOutDetailInfo();
        Map<Integer, InOutTypeInfo> inOutTypeInfoMap = getInOutMap();
        inOutDetailInfo.setCreateUserId(userId);
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<InOutDetailInfo> outDetailInfoList = inOutDetailInfoMapper.selectSelective(inOutDetailInfo);
        for (InOutDetailInfo inOutDetailInfo1 : outDetailInfoList) {
            InOutDetailVO inOutDetailVO = new InOutDetailVO();
            BeanUtils.copyProperties(inOutDetailInfo1, inOutDetailVO);
            if (inOutTypeInfoMap.get(inOutDetailInfo1.getInoutTypeId()) != null) {
                InOutTypeInfo inOutTypeInfo = inOutTypeInfoMap.get(inOutDetailInfo1.getInoutTypeId());
                inOutDetailVO.setInoutTypeDesc(inOutTypeInfo.getDescription());
                inOutDetailVO.setInoutTypeIcon(inOutTypeInfo.getIcon());
            }
            inOutDetailVOList.add(inOutDetailVO);
        }
        inOutResponse.setInOutDetailVOList(inOutDetailVOList);
        return inOutResponse;
    }

    public Map<Integer, InOutTypeInfo> getInOutMap() {
        Map<Integer, InOutTypeInfo> inOutTypeInfoMap = new HashMap<>();
        List<InOutTypeInfo> inOutTypeInfoList = getAll();
        for (InOutTypeInfo inOutTypeInfo : inOutTypeInfoList) {
            inOutTypeInfoMap.put(inOutTypeInfo.getId(), inOutTypeInfo);
        }
        return inOutTypeInfoMap;
    }
}
