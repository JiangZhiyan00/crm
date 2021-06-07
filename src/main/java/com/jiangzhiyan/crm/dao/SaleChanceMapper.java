package com.jiangzhiyan.crm.dao;

import com.jiangzhiyan.crm.base.BaseMapper;
import com.jiangzhiyan.crm.vo.SaleChance;

/**
 * @author JiangZhiyan
 */
public interface SaleChanceMapper extends BaseMapper<SaleChance,Integer> {
    /**
     * 更新开发状态
     * @param saleChance
     * @return
     */
    Integer updateDevResult(SaleChance saleChance);
}