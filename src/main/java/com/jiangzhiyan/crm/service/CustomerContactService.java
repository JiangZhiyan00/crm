package com.jiangzhiyan.crm.service;

import com.jiangzhiyan.crm.base.BaseService;
import com.jiangzhiyan.crm.dao.CustomerContactMapper;
import com.jiangzhiyan.crm.utils.AssertUtil;
import com.jiangzhiyan.crm.vo.CustomerContact;
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
public class CustomerContactService extends BaseService<CustomerContact,Integer> {

    @Resource
    private CustomerContactMapper customerContactMapper;

    /**
     * 添加客户交流记录
     * @param customerContact 待添加的客户交流记录
     */
    @Transactional(rollbackFor = Exception.class)
    public void addCustomerContact(CustomerContact customerContact) {
        checkAddOrUpdateParams(customerContact);
        initAddOrUpdateParams(customerContact);
        Integer result = customerContactMapper.insertSelective(customerContact);
        AssertUtil.isTrue(result != 1,"服务器异常!记录添加失败...");
    }

    /**
     * 更新客户交流记录
     * @param customerContact 待更新的客户交流记录
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateCustomerContact(CustomerContact customerContact) {
        checkAddOrUpdateParams(customerContact);
        initAddOrUpdateParams(customerContact);
        Integer result = customerContactMapper.updateByPrimaryKeySelective(customerContact);
        AssertUtil.isTrue(result != 1,"服务器异常!记录更新失败...");
    }

    /**
     * 批量删除客户交流记录
     * @param ids 待删除的记录id集合
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteCustomerContacts(List<Integer> ids) {
        AssertUtil.isTrue(ids == null || ids.size() == 0,"无效的请求!请重试...");
        Integer result = customerContactMapper.deleteBatch(ids);
        AssertUtil.isTrue(result != ids.size(),"服务器异常!记录删除失败...");
    }

//--------------------------------------分隔线--------------------------------------------------------------------

    /**
     * 添加/更新客户交流记录参数校验
     * @param customerContact 待添加/更新的记录
     */
    private void checkAddOrUpdateParams(CustomerContact customerContact) {
        AssertUtil.isTrue(customerContact == null,"无效的请求!请重试...");
        AssertUtil.isTrue(customerContact.getContactTime() == null,"交流时间不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(customerContact.getAddress()),"交流地址不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(customerContact.getOverview()),"交流概要不能为空!");
    }

    /**
     * 添加/更新客户交流记录参数初始化
     * @param customerContact 待添加/更新的记录
     */
    private void initAddOrUpdateParams(CustomerContact customerContact) {
        if (customerContact.getId() == null){
            customerContact.setIsValid(1);
            customerContact.setCreateDate(new Date());
        }else {
            customerContact.setUpdateDate(new Date());
        }
    }
}
