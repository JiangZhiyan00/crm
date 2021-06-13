package com.jiangzhiyan.crm.dao;

import com.jiangzhiyan.crm.base.BaseMapper;
import com.jiangzhiyan.crm.vo.CustomerLoss;

/**
 * @author JiangZhiyan
 */
public interface CustomerLossMapper extends BaseMapper<CustomerLoss,Integer> {
    /**
     * 查询所有为暂缓状态:1的客户
     * @return 暂缓状态客户总数
     */
    Integer selectStateIs1Count();

    /**
     * 删除所有状态为暂缓的客户
     * @return 删除后数据改变数目
     */
    Integer deleteAllStateIs1();
}