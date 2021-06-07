package com.jiangzhiyan.crm.service;

import com.jiangzhiyan.crm.base.BaseService;
import com.jiangzhiyan.crm.dao.CustomerDevPlanMapper;
import com.jiangzhiyan.crm.dao.SaleChanceMapper;
import com.jiangzhiyan.crm.utils.AssertUtil;
import com.jiangzhiyan.crm.vo.CustomerDevPlan;
import com.jiangzhiyan.crm.vo.SaleChance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CustomerDevPlanService extends BaseService<CustomerDevPlan,Integer> {

    @Resource
    private CustomerDevPlanMapper customerDevPlanMapper;

    @Resource
    private SaleChanceMapper saleChanceMapper;

    /**
     * 添加开发计划
     * @param entity
     * @return
     * @throws DataAccessException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insertSelective(CustomerDevPlan entity) throws DataAccessException {
        checkInsertParams(entity);
        initInsertDevPlan(entity);
        Integer result = super.insertSelective(entity);
        AssertUtil.isTrue(result != 1,"添加失败!请重试...");
        return result;
    }

    /**
     * 更新开发计划
     * @param entity
     * @return
     * @throws DataAccessException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateByPrimaryKeySelective(CustomerDevPlan entity) throws DataAccessException {
        checkInsertParams(entity);
        entity.setUpdateDate(new Date());
        Integer result = super.updateByPrimaryKeySelective(entity);
        AssertUtil.isTrue(result != 1,"更新失败!请重试...");
        return result;
    }

    /**
     * 删除单条计划
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deletePlanById(Integer id){
        AssertUtil.isTrue(id == null,"不存在此计划项!");
        Integer result = customerDevPlanMapper.deletePlanById(id);
        AssertUtil.isTrue(result != 1,"删除失败!请重试...");
    }

    /**
     * 设置开发状态
     * @param plan
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateSaleChanceDevResult(CustomerDevPlan plan){

    }

    /**
     * 添加/更新操作参数校验
     * @param plan
     */
    private void checkInsertParams(CustomerDevPlan plan){
        AssertUtil.isTrue(plan == null || plan.getSaleChanceId() == null,"请求无数据!请重试!");
        AssertUtil.isTrue(StringUtils.isBlank(plan.getPlanItem()),"计划项不能为空!");
        AssertUtil.isTrue(plan.getPlanDate() == null,"执行时间不能为空");
        SaleChance saleChance = saleChanceMapper.selectByPrimaryKey(plan.getSaleChanceId());
        AssertUtil.isTrue(saleChance == null,"不存在该条销售机会!");
    }

    /**
     * 添加操作参数初始化
     * @param plan
     */
    private void initInsertDevPlan(CustomerDevPlan plan){
        plan.setCreateDate(new Date());
        plan.setIsValid(1);
    }
}
