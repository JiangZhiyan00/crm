package com.jiangzhiyan.crm.dao;

import com.jiangzhiyan.crm.base.BaseMapper;
import com.jiangzhiyan.crm.query.CustomerQuery;
import com.jiangzhiyan.crm.vo.Customer;

import java.util.List;
import java.util.Map;

/**
 * @author JiangZhiyan
 */
public interface CustomerMapper extends BaseMapper<Customer,Integer> {

    List<Customer> selectByParams(CustomerQuery query);

    Customer selectByCustomerName(String name);

    /**
     * 查询所有待流失客户
     * 流失要求:客户创建时间满6个月且近6个月没有订单
     * @return 待流失的客户集合
     */
    List<Customer> selectWaitToLossCustomers();

    /**
     * 将流失客户的状态改为流失:0
     * @param lossCustomerIds 流失客户的id集合
     * @return 数据改动条目数
     */
    Integer updateState0ByIds(List<Integer> lossCustomerIds);

    Integer updateState1ByNo(String cusNo);

    Integer updateState0ByNo(String cusNo);

    List<Map<String, Object>> selectAllCustomersAndSelected(Integer serveId);
}