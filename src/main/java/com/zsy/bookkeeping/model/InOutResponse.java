package com.zsy.bookkeeping.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by Administrator on 2018/8/13.
 */
@Data
@ToString
public class InOutResponse {
    private List<InOutDetailVO> inOutDetailVOList;
}
