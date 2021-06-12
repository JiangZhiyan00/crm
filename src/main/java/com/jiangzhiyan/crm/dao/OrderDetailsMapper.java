package com.jiangzhiyan.crm.dao;

import com.jiangzhiyan.crm.base.BaseMapper;
import com.jiangzhiyan.crm.vo.OrderDetails;

import java.util.List;

/**
 * @author JiangZhiyan
 */
public interface OrderDetailsMapper extends BaseMapper<OrderDetails,Integer> {

    Integer selectCountByOrderIds(List<Integer> orderIds);

    Integer deleteByOrderIds(List<Integer> orderIds);
}