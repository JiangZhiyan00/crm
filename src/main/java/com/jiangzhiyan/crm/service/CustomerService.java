package com.jiangzhiyan.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiangzhiyan.crm.base.BaseService;
import com.jiangzhiyan.crm.dao.CustomerMapper;
import com.jiangzhiyan.crm.dao.CustomerOrderMapper;
import com.jiangzhiyan.crm.dao.OrderDetailsMapper;
import com.jiangzhiyan.crm.query.CustomerContributionQuery;
import com.jiangzhiyan.crm.utils.AssertUtil;
import com.jiangzhiyan.crm.utils.PhoneUtil;
import com.jiangzhiyan.crm.vo.Customer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author JiangZhiyan
 */
@Service
public class CustomerService extends BaseService<Customer,Integer> {

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private CustomerOrderMapper customerOrderMapper;

    @Resource
    private OrderDetailsMapper orderDetailsMapper;

    /**
     * 添加客户信息
     * @param customer 待添加的客户对象
     */
    @Transactional(rollbackFor = Exception.class)
    public void addCustomer(Customer customer) {
        checkAddOrUpdateCustomerParams(customer);
        initAddOrUpdateCustomerParams(customer);
        Integer result = customerMapper.insertSelective(customer);
        AssertUtil.isTrue(result != 1,"客户信息添加失败!");
    }

    /**
     * 更新客户信息
     * @param customer 待更新的客户信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateCustomer(Customer customer) {
        checkAddOrUpdateCustomerParams(customer);
        initAddOrUpdateCustomerParams(customer);
        Integer result = customerMapper.updateByPrimaryKeySelective(customer);
        AssertUtil.isTrue(result != 1,"客户信息更新失败!");
    }

    /**
     * 删除客户信息(实际是更新操作,改为无效数据)
     * @param ids 待删除的客户信息id集合
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteCustomersByIds(List<Integer> ids) {
        AssertUtil.isTrue(ids == null || ids.size() == 0,"无效的请求!请重试...");
        //1.删除详细订单中的所有关联记录
        List<Integer> orderIds = customerOrderMapper.selectOrderIdsByCusIds(ids);
        if (orderIds != null && orderIds.size() > 0){
            Integer orderDetailsCount = orderDetailsMapper.selectCountByOrderIds(orderIds);
            Integer deleteOrderDetailsResult = orderDetailsMapper.deleteByOrderIds(orderIds);
            AssertUtil.isTrue(!orderDetailsCount.equals(deleteOrderDetailsResult),"服务器异常!删除失败...");
            //2.删除客户订单中的管理记录
            Integer ordersCount = customerOrderMapper.selectCountByCusIds(ids);
            Integer deleteOrdersResult = customerOrderMapper.deleteByCusIds(ids);
            AssertUtil.isTrue(!ordersCount.equals(deleteOrdersResult),"服务器异常!删除失败...");
        }
        //3.删除客户
        Integer result = customerMapper.deleteBatch(ids);
        AssertUtil.isTrue(result != ids.size(),"服务器异常!客户信息删除失败...");
    }

    public List<Map<String, Object>> selectAllCustomersAndSelected(Integer serveId) {
        return customerMapper.selectAllCustomersAndSelected(serveId);
    }

    /**
     * 条件查询客户贡献
     * @param query 查询条件
     * @return Map集合:key:cusName(客户名);value:total(总金额)
     */
    public Map<String, Object> queryContribByParams(CustomerContributionQuery query) {
        if (query != null && query.getMoneyLevel() != null){
            if (query.getMoneyLevel() == 1){
                query.setMinMoney(BigDecimal.ZERO);
                query.setMaxMoney(new BigDecimal(50000));
            }else if (query.getMoneyLevel() == 2){
                query.setMinMoney(new BigDecimal(50000));
                query.setMaxMoney(new BigDecimal(100000));
            }else if (query.getMoneyLevel() == 3){
                query.setMinMoney(new BigDecimal(100000));
                query.setMaxMoney(new BigDecimal(500000));
            }else {
                query.setMinMoney(new BigDecimal(500000));
            }
        }
        PageHelper.startPage(query.getPage(),query.getLimit());
        List<Map<String,Object>> contributions = customerMapper.queryContribByParams(query);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(contributions);
        Map<String,Object> map = new HashMap<>(4);
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    /**
     * 条件查询客户贡献,返回饼状图数据格式
     * @param query 查询条件
     * @return 饼状图数据格式
     */
    public List<Map<String, Object>> getCustomerContrib(CustomerContributionQuery query) {
        if (query != null && query.getMoneyLevel() != null){
            if (query.getMoneyLevel() == 1){
                query.setMinMoney(BigDecimal.ZERO);
                query.setMaxMoney(new BigDecimal(50000));
            }else if (query.getMoneyLevel() == 2){
                query.setMinMoney(new BigDecimal(50000));
                query.setMaxMoney(new BigDecimal(100000));
            }else if (query.getMoneyLevel() == 3){
                query.setMinMoney(new BigDecimal(100000));
                query.setMaxMoney(new BigDecimal(500000));
            }else {
                query.setMinMoney(new BigDecimal(500000));
            }
        }
        return customerMapper.getCustomerContrib(query);
    }

    /**
     * 查询客户构成
     * @return 返回条形图所需格式
     */
    public Map<String, Object> countCustomerLevelForHistogram() {
        List<Map<String,Object>> mapList = customerMapper.countCustomerLevelForHistogram();
        List<Object> nameList = new ArrayList<>();
        List<Object> valueList = new ArrayList<>();
        mapList.forEach(m -> {
            nameList.add(m.get("name"));
            valueList.add(m.get("value"));
        });
        Map<String,Object> map = new HashMap<>(2);
        map.put("names",nameList);
        map.put("values",valueList);
        return map;
    }

    /**
     * 查询客户构成
     * @return 返回饼图需要的格式
     */
    public List<Map<String, Object>> countCustomerLevelForPie() {
        return customerMapper.countCustomerLevelForHistogram();
    }

    /**
     * 查询客户服务类型(咨询/建议/投诉)
     * @return 饼图需要的格式
     */
    public List<Map<String, Object>> getServeTypeForPie() {
        return customerMapper.getServeTypeForPie();
    }

    /**
     * 查询客户服务满意度
     * @return 饼图需要的格式
     */
    public List<Map<String, Object>> getSatisfactionForPie() {
        return customerMapper.getSatisfactionForPie();
    }

    /**
     *查询流失客户类型和数量
     * @return 饼图需要的格式
     */
    public List<Map<String, Object>> getLossCustomerForPie() {
        return customerMapper.getLossCustomerForPie();
    }

//------------------------------------分隔线(下方主要为参数校验及初始化)---------------------------------------------------

    /**
     * 添加/更新客户信息输入参数校验
     * @param customer 前端请求中接收的客户对象
     */
    private void checkAddOrUpdateCustomerParams(Customer customer) {
        AssertUtil.isTrue(customer == null,"无效的请求!请重试...");
        AssertUtil.isTrue(StringUtils.isBlank(customer.getName()),"客户名称不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(customer.getPhone()),"客户联系电话不能为空!");
        AssertUtil.isTrue(!PhoneUtil.isMobile(customer.getPhone()),"客户联系电话格式不正确!");
        AssertUtil.isTrue(StringUtils.isBlank(customer.getLegalPerson()),"客户法人不能为空!");
        Customer existCustomer = customerMapper.selectByCustomerName(customer.getName());
        //添加
        if (customer.getId() == null){
            AssertUtil.isTrue(existCustomer != null,"该名称客户已存在!");
        //更新
        }else {
            AssertUtil.isTrue(existCustomer != null && !customer.getId().equals(existCustomer.getId()),
                    "该名称客户已存在!");
        }
    }

    /**
     * 初始化添加/更新客户信息参数
     * @param customer 前端请求中接收的客户对象
     */
    private void initAddOrUpdateCustomerParams(Customer customer) {
        //添加
        if (customer.getId() == null){
            customer.setCustomerId(getCustomerId());
            customer.setIsValid(1);
            customer.setState(1);
            customer.setCreateDate(new Date());
        //更新
        }else {
            customer.setUpdateDate(new Date());
        }
    }

    /**
     * 通过系统当前时间生成客户编号
     * @return 客户编号如:"KH20210610122555859"
     */
    private String getCustomerId(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return "KH"+sdf.format(new Date());
    }
}
