package com.jiangzhiyan.crm.service;

import com.jiangzhiyan.crm.base.BaseService;
import com.jiangzhiyan.crm.dao.CustomerLinkManMapper;
import com.jiangzhiyan.crm.utils.AssertUtil;
import com.jiangzhiyan.crm.utils.PhoneUtil;
import com.jiangzhiyan.crm.vo.CustomerLinkMan;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author JiangZhiyan
 */
@Service
public class CustomerLinkManService extends BaseService<CustomerLinkMan,Integer> {

    @Resource
    private CustomerLinkManMapper customerLinkManMapper;

    /**
     * 通过客户id查询对应联系人信息
     * @param cusId 客户id
     * @return 联系人对象(无联系人返回null)
     */
    public CustomerLinkMan selectByCusId(Integer cusId) {
        AssertUtil.isTrue(cusId == null,"无效的请求!请重试...");
        return customerLinkManMapper.selectByCusId(cusId);
    }

    /**
     * 添加客户联系人信息
     * @param customerLinkMan 待添加的客户联系人信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void addCustomerLinkMan(CustomerLinkMan customerLinkMan) {
        checkAddOrUpdateLinkManParams(customerLinkMan);
        initAddOrUpdateLinkManParams(customerLinkMan);
        Integer result = customerLinkManMapper.insertSelective(customerLinkMan);
        AssertUtil.isTrue(result != 1,"服务器异常!添加失败...");
    }

    /**
     * 更新客户联系人信息
     * @param customerLinkMan 待更新的客户联系人信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateCustomerLinkMan(CustomerLinkMan customerLinkMan){
        checkAddOrUpdateLinkManParams(customerLinkMan);
        initAddOrUpdateLinkManParams(customerLinkMan);
        Integer result = customerLinkManMapper.updateByPrimaryKeySelective(customerLinkMan);
        AssertUtil.isTrue(result != 1,"服务器异常!更新失败...");
    }

    /**
     * 添加/更新客户联系人信息参数校验
     * @param customerLinkMan 待添加/更新的客户联系人对象
     */
    private void checkAddOrUpdateLinkManParams(CustomerLinkMan customerLinkMan) {
        AssertUtil.isTrue(customerLinkMan == null,"无效的请求!请重试...");
        AssertUtil.isTrue(StringUtils.isBlank(customerLinkMan.getLinkName()),"联系人姓名不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(customerLinkMan.getSex()),"联系人性别不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(customerLinkMan.getPosition()),"联系人职位不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(customerLinkMan.getPhone()),"联系人手机号不能为空!");
        AssertUtil.isTrue(!PhoneUtil.isMobile(customerLinkMan.getPhone()),"联系人手机号格式错误!");
        //添加
        if (customerLinkMan.getId() == null) {
            AssertUtil.isTrue(customerLinkManMapper.selectByCusId(customerLinkMan.getCusId()) != null,
                    "该客户已有联系人!");
        }
    }

    /**
     * 添加/更新客户联系人参数初始化
     * @param customerLinkMan 待添加/更新的客户联系人对象
     */
    private void initAddOrUpdateLinkManParams(CustomerLinkMan customerLinkMan) {
        if (customerLinkMan.getId() == null){
            customerLinkMan.setIsValid(1);
            customerLinkMan.setCreateDate(new Date());
        }else {
            customerLinkMan.setUpdateDate(new Date());
        }
    }
}
