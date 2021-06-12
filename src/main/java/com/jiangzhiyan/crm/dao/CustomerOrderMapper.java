package com.jiangzhiyan.crm.dao;

import com.jiangzhiyan.crm.base.BaseMapper;
import com.jiangzhiyan.crm.vo.CustomerOrder;

import java.util.List;

/**
 * @author JiangZhiyan
 */
public interface CustomerOrderMapper extends BaseMapper<CustomerOrder,Integer> {

    Integer selectCountByCusIds(List<Integer> cusIds);

    Integer deleteByCusIds(List<Integer> cusIds);

    List<Integer> selectOrderIdsByCusIds(List<Integer> cusIds);
}