package com.jiangzhiyan.crm.service;

import com.jiangzhiyan.crm.base.BaseService;
import com.jiangzhiyan.crm.base.ResultInfo;
import com.jiangzhiyan.crm.dao.SaleChanceMapper;
import com.jiangzhiyan.crm.enums.DevResult;
import com.jiangzhiyan.crm.enums.StateStatus;
import com.jiangzhiyan.crm.utils.AssertUtil;
import com.jiangzhiyan.crm.utils.PhoneUtil;
import com.jiangzhiyan.crm.vo.SaleChance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SaleChanceService extends BaseService<SaleChance,Integer> {

    @Resource
    private SaleChanceMapper saleChanceMapper;

    /**
     * 添加单个销售机会
     * @param saleChance
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo addSaleChance(SaleChance saleChance){
        Integer result = null;
        ResultInfo resultInfo = new ResultInfo();
        checkSaleChanceParams(saleChance);
        result = saleChanceMapper.insertSelective(initAddSaleChanceParams(saleChance));
        AssertUtil.isTrue(result != 1,"服务器异常,添加失败!");
        return resultInfo;
    }

    /**
     * 更新单条销售机会
     * @param saleChance
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo updateSaleChance(SaleChance saleChance){

        Integer result = null;
        ResultInfo resultInfo = new ResultInfo();
        checkSaleChanceParams(saleChance);
        initUpdateSaleChanceParams(saleChance);
        result = saleChanceMapper.updateByPrimaryKeySelective(saleChance);
        AssertUtil.isTrue(result != 1,"服务器异常,更新失败!");
        return resultInfo;
    }

    /**
     * 删除单条营销机会数据
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteBatch(List<Integer> ids){
        AssertUtil.isTrue(ids == null || ids.size() == 0,"请先选择要删除的数据!");
        Integer result = null;
        result = saleChanceMapper.deleteBatch(ids);
        AssertUtil.isTrue(result != ids.size(),"服务器异常,删除失败!");
        return result;
    }

    /**
     * 更改开发状态
     * @param saleChance
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateDevResult(SaleChance saleChance) {
        checkUpdateDevResultParams(saleChance);
        saleChance.setUpdateDate(new Date());
        Integer result = saleChanceMapper.updateDevResult(saleChance);
        AssertUtil.isTrue(result != 1,"服务器异常,更改失败!");
    }

    /**
     * 参数校验
     * @param saleChance
     */
    private void checkSaleChanceParams(SaleChance saleChance){
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getCustomerName()),"客户名称不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getLinkMan()),"联系人不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getLinkPhone()),"联系电话不能为空!");
        AssertUtil.isTrue(!PhoneUtil.isMobile(saleChance.getLinkPhone()),"联系电话格式错误!");
    }

    /**
     * 添加操作数据初始化
     * @param saleChance
     * @return
     */
    private SaleChance initAddSaleChanceParams(SaleChance saleChance){

        saleChance.setCreateDate(new Date());
        if (StringUtils.isNotBlank(saleChance.getAssignMan())){
            saleChance.setAssignTime(new Date());
            saleChance.setState(StateStatus.STATED.getStatus());
            saleChance.setDevResult(DevResult.IN_DEV.getDevResult());
        }else {
            saleChance.setState(StateStatus.UN_STATED.getStatus());
            saleChance.setDevResult(DevResult.UN_DEV.getDevResult());
        }
        return saleChance;
    }

    /**
     * 更新操作数据初始化
     * @param saleChance
     * @return
     */
    private SaleChance initUpdateSaleChanceParams(SaleChance saleChance){
        SaleChance oriSaleChance = saleChanceMapper.selectByPrimaryKey(saleChance.getId());
        AssertUtil.isTrue(oriSaleChance == null,"待更新的数据不存在");
        saleChance.setUpdateDate(new Date());
        if (StringUtils.isBlank(oriSaleChance.getAssignMan())){
            if (!StringUtils.isBlank(saleChance.getAssignMan())){
                saleChance.setAssignTime(new Date());
                saleChance.setState(StateStatus.STATED.getStatus());
                saleChance.setDevResult(DevResult.IN_DEV.getDevResult());
            }else {
                saleChance.setAssignTime(null);
                saleChance.setState(StateStatus.UN_STATED.getStatus());
                saleChance.setDevResult(DevResult.UN_DEV.getDevResult());
            }
        }else {
            if (StringUtils.isBlank(saleChance.getAssignMan())){
                saleChance.setAssignTime(null);
                saleChance.setState(StateStatus.UN_STATED.getStatus());
                saleChance.setDevResult(DevResult.UN_DEV.getDevResult());
            }else {
                if (!oriSaleChance.getAssignMan().equals(saleChance.getAssignMan())) {
                    saleChance.setAssignTime(new Date());
                }else {
                    //当原指派人与新指派人相同时,指派时间不变
                    saleChance.setAssignTime(oriSaleChance.getAssignTime());
                }
            }
        }
        return saleChance;
    }

    /**
     * 更新开发状态参数校验
     * @param saleChance
     */
    private void checkUpdateDevResultParams(SaleChance saleChance){
        AssertUtil.isTrue(saleChance == null ||
                saleChance.getId() == null ||
                saleChance.getDevResult() == null,"请求参数无效!请重试...");
    }
}
