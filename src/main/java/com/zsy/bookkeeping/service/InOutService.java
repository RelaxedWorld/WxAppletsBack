package com.zsy.bookkeeping.service;

import com.zsy.bookkeeping.model.InOutDetailInfo;
import com.zsy.bookkeeping.model.InOutResponse;
import com.zsy.bookkeeping.model.InOutTypeInfo;

import java.util.List;

/**
 * The interface In out service.
 */
public interface InOutService {
    /**
     * 获取所有收支类型信息列表.
     *
     * @return the all
     */
    List<InOutTypeInfo> getAll();

    /**
     * 根据收入还是支出类别获取收支类型信息列表.
     *
     * @param inOut the in out
     * @return the by in out type
     */
    List<InOutTypeInfo> getByInOutType(Integer inOut);

    /**
     * 通过主键id获取收支类型信息.
     *
     * @param id the id
     * @return the by id
     */
    InOutTypeInfo getById(Integer id);

    /**
     * 新增一条收支类型.
     *
     * @param inOutTypeInfo the in out type info
     * @return the boolean
     */
    boolean insert(InOutTypeInfo inOutTypeInfo);

    /**
     * 根据主键id更新收支类型信息.
     *
     * @param inOutTypeInfo the in out type info
     * @return the boolean
     */
    boolean update(InOutTypeInfo inOutTypeInfo);

    /**
     * 新增一条收支明细.
     *
     * @param inOutDetailInfo the in out detail info
     * @return the boolean
     */
    boolean insert(InOutDetailInfo inOutDetailInfo);

    /**
     * 根据主键id更新收支明细.
     *
     * @param inOutDetailInfo the in out detail info
     * @return the boolean
     */
    boolean update(InOutDetailInfo inOutDetailInfo);

    /**
     * 分页查询收支明细列表.
     *
     * @param userId   the user id
     * @param pageNum  the page num
     * @param pageSize the page size
     * @return the page by user id
     */
    InOutResponse getPageByUserId(String userId, int pageNum, int pageSize);
}
