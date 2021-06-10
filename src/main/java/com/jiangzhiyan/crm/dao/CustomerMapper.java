package com.jiangzhiyan.crm.dao;

import com.jiangzhiyan.crm.base.BaseMapper;
import com.jiangzhiyan.crm.query.CustomerQuery;
import com.jiangzhiyan.crm.vo.Customer;

import java.util.List;

/**
 * @author JiangZhiyan
 */
public interface CustomerMapper extends BaseMapper<Customer,Integer> {

    List<Customer> selectByParams(CustomerQuery query);

    Customer selectByCustomerName(String name);

}