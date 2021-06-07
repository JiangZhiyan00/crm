package com.jiangzhiyan.crm.dao;

import com.jiangzhiyan.crm.base.BaseMapper;
import com.jiangzhiyan.crm.query.DevPlanQuery;
import com.jiangzhiyan.crm.vo.CustomerDevPlan;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JiangZhiyan
 */
public interface CustomerDevPlanMapper extends BaseMapper<CustomerDevPlan,Integer> {
    /**
     * 根据销售机会id查询此销售机会的所有计划项
     * @param query
     * @return
     * @throws DataAccessException
     */
    List<CustomerDevPlan> selectPlanBySaleChanceId(DevPlanQuery query) throws DataAccessException;

    /**
     * 根据id删除计划(实际上是更新)
     * @param id
     * @return
     */
    Integer deletePlanById(Integer id);
}