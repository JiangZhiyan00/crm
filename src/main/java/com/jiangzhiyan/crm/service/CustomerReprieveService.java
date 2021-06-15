package com.jiangzhiyan.crm.service;

import com.jiangzhiyan.crm.base.BaseService;
import com.jiangzhiyan.crm.dao.CustomerLossMapper;
import com.jiangzhiyan.crm.dao.CustomerMapper;
import com.jiangzhiyan.crm.dao.CustomerReprieveMapper;
import com.jiangzhiyan.crm.utils.AssertUtil;
import com.jiangzhiyan.crm.vo.CustomerLoss;
import com.jiangzhiyan.crm.vo.CustomerReprieve;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author JiangZhiyan
 */
@Service
public class CustomerReprieveService extends BaseService<CustomerReprieve,Integer> {

    @Resource
    private CustomerReprieveMapper customerReprieveMapper;

    @Resource
    private CustomerLossMapper customerLossMapper;

    @Resource
    private CustomerMapper customerMapper;

    /**
     * 首次添加暂缓措施
     * @param customerReprieve 待添加的暂缓措施
     */
    @Transactional(rollbackFor = Exception.class)
    public void firstAddReprieve(CustomerReprieve customerReprieve) {
        checkAddOrUpdateParams(customerReprieve);
        CustomerLoss loss = customerLossMapper.selectState0LossCustomerById(customerReprieve.getLossId());
        AssertUtil.isTrue(loss == null,"该流失客户不存在!");
        initAddOrUpdateParams(customerReprieve);
        List<CustomerReprieve> existReprieves = customerReprieveMapper.selectByLossId(customerReprieve.getLossId());
        //如果是首次添加暂缓措施
        if (existReprieves == null || existReprieves.size() == 0){
            //1.将流失客户改为暂缓状态:state=1
            Integer updateLossResult = customerLossMapper.setState1ById(customerReprieve.getLossId());
            AssertUtil.isTrue(updateLossResult != 1,"服务器异常!添加失败...");
            //2.将客户表中对应客户state改为1
            Integer updateCustomerResult = customerMapper.updateState1ByNo(loss.getCusNo());
            AssertUtil.isTrue(updateCustomerResult != 1,"服务器异常!添加失败...");
        }
        //添加暂缓措施
        Integer addResult = customerReprieveMapper.insertSelective(customerReprieve);
        AssertUtil.isTrue(addResult != 1,"服务器异常!添加失败...");
    }

    /**
     * 添加暂缓措施
     * @param customerReprieve 待添加的暂缓措施
     */
    @Transactional(rollbackFor = Exception.class)
    public void addReprieve(CustomerReprieve customerReprieve) {
        checkAddOrUpdateParams(customerReprieve);
        CustomerLoss loss = customerLossMapper.selectState1LossCustomerById(customerReprieve.getLossId());
        AssertUtil.isTrue(loss == null,"该暂缓客户不存在!");
        initAddOrUpdateParams(customerReprieve);
        Integer result = customerReprieveMapper.insertSelective(customerReprieve);
        AssertUtil.isTrue(result != 1,"服务器异常!添加失败...");
    }

    /**
     * 更新暂缓措施
     * @param customerReprieve 待更新的暂缓措施
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateReprieve(CustomerReprieve customerReprieve) {
        checkAddOrUpdateParams(customerReprieve);
        CustomerLoss loss = customerLossMapper.selectState1LossCustomerById(customerReprieve.getLossId());
        AssertUtil.isTrue(loss == null,"该暂缓客户不存在!");
        initAddOrUpdateParams(customerReprieve);
        Integer result = customerReprieveMapper.updateByPrimaryKeySelective(customerReprieve);
        AssertUtil.isTrue(result != 1,"服务器异常!更新失败...");
    }

    /**
     * 取消暂缓
     * @param lossId 待取消暂缓的流失客户id
     */
    @Transactional(rollbackFor = Exception.class)
    public void cancelReprieveByLossId(Integer lossId) {
        AssertUtil.isTrue(lossId == null,"无效的请求!请重试...");
        CustomerLoss loss = customerLossMapper.selectState1LossCustomerById(lossId);
        AssertUtil.isTrue(loss == null,"该暂缓客户不存在!");
        //1.将暂缓措施中lossId对应的所有数据删除
        Integer count = customerReprieveMapper.selectCountByLossId(lossId);
        Integer result = customerReprieveMapper.cancelReprieveByLossId(lossId);
        AssertUtil.isTrue(!count.equals(result),"服务器异常!取消暂缓失败!");
        //2.将lossId对应的流失客户状态改为流失:0
        Integer setState0Result = customerLossMapper.setState0ById(lossId);
        AssertUtil.isTrue(setState0Result != 1,"服务器异常!取消暂缓失败!");
        //3.将客户表中对应的客户设置为流失状态
        Integer updateCustomerResult = customerMapper.updateState0ByNo(loss.getCusNo());
        AssertUtil.isTrue(updateCustomerResult != 1,"服务器异常!取消暂缓失败!");
    }

    /**
     * 删除暂缓措施
     * @param ids 待删除的暂缓措施id集合
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteReprieves(List<Integer> ids) {
        AssertUtil.isTrue(ids == null || ids.size() < 1,"无效的请求!请重试...");
        Integer result = customerReprieveMapper.deleteBatch(ids);
        AssertUtil.isTrue(result != ids.size(),"服务器异常!删除失败...");
    }

//-------------------------------------分隔线----------------------------------------------------------------

    /**
     * 添加/更新暂缓措施参数校验
     * @param customerReprieve 待添加/更新的暂缓措施
     */
    private void checkAddOrUpdateParams(CustomerReprieve customerReprieve) {
        AssertUtil.isTrue(customerReprieve == null,"无效的请求!请重试...");
        AssertUtil.isTrue(StringUtils.isBlank(customerReprieve.getMeasure()),"暂缓措施不能为空!");
    }

    /**
     * 添加/更新暂缓措施参数初始化
     * @param customerReprieve 待添加/更新的暂缓措施
     */
    private void initAddOrUpdateParams(CustomerReprieve customerReprieve) {
        if (customerReprieve.getId() == null){
            customerReprieve.setIsValid(1);
            customerReprieve.setCreateDate(new Date());
        }else {
            customerReprieve.setUpdateDate(new Date());
        }
    }
}
