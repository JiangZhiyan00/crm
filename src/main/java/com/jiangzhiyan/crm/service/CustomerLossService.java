package com.jiangzhiyan.crm.service;

import com.jiangzhiyan.crm.base.BaseService;
import com.jiangzhiyan.crm.dao.CustomerLossMapper;
import com.jiangzhiyan.crm.dao.CustomerMapper;
import com.jiangzhiyan.crm.dao.CustomerOrderMapper;
import com.jiangzhiyan.crm.utils.AssertUtil;
import com.jiangzhiyan.crm.vo.Customer;
import com.jiangzhiyan.crm.vo.CustomerLoss;
import com.jiangzhiyan.crm.vo.CustomerOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author JiangZhiyan
 */
@Service
public class CustomerLossService extends BaseService<CustomerLoss,Integer> {

    @Resource
    private CustomerLossMapper customerLossMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private CustomerOrderMapper customerOrderMapper;

    /**
     * 将客户表中满足流失条件的客户流失(定时执行)
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateCustomerLossTable(){
        //1.查询所有待流失的客户
        List<Customer> waitToLossCustomers = customerMapper.selectWaitToLossCustomers();
        if (waitToLossCustomers != null && waitToLossCustomers.size()>0){
            //2.初始化流失客户信息
            List<CustomerLoss> customerLosses = new ArrayList<>();
            List<Integer> lossCustomerIds = new ArrayList<>();
            waitToLossCustomers.forEach(customer -> {
                CustomerLoss customerLoss = new CustomerLoss();
                customerLoss.setCusNo(customer.getCustomerId());
                customerLoss.setCusName(customer.getName());
                customerLoss.setCusManager(customer.getCusManager());
                //查询此客户最后一次订单下单日期
                CustomerOrder lastOrder = customerOrderMapper.selectLastOrderByCusId(customer.getId());
                if (lastOrder != null){
                    customerLoss.setLastOrderTime(lastOrder.getOrderDate());
                }
                customerLoss.setConfirmLossTime(new Date());
                //初始全部定义为流失状态:0
                customerLoss.setState(0);
                customerLoss.setLossReason("6个月无订单自动流失");
                customerLoss.setIsValid(1);
                customerLoss.setCreateDate(new Date());
                customerLosses.add(customerLoss);
                lossCustomerIds.add(customer.getId());
            });
            //3.插入流失客户前,先删除所有暂缓状态:1的客户
            Integer count = customerLossMapper.selectStateIs1Count();
            if (count != null && count >0){
                Integer deleteLossResult = customerLossMapper.deleteAllStateIs1();
                AssertUtil.isTrue(!count.equals(deleteLossResult),"暂缓客户删除失败!");
            }
            //4.插入流失客户
            Integer insertLossResult = customerLossMapper.insertBatch(customerLosses);
            AssertUtil.isTrue(insertLossResult != customerLosses.size(),"流失客户添加失败!");
            //5.将客户表中本次流失的客户状态改为流失
            Integer updateResult = customerMapper.updateState0ByIds(lossCustomerIds);
            AssertUtil.isTrue(updateResult != lossCustomerIds.size(),"客户流失状态更改失败!");
        }
    }

    /**
     * 根据流失客户id查询对应数据
     * @param id 流失客户id
     * @return 流失客户对象
     */
    public CustomerLoss selectState0LossCustomerById(Integer id) {
        return customerLossMapper.selectState0LossCustomerById(id);
    }
}
