package com.jiangzhiyan.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiangzhiyan.crm.base.BaseQuery;
import com.jiangzhiyan.crm.base.BaseService;
import com.jiangzhiyan.crm.dao.CustomerOrderMapper;
import com.jiangzhiyan.crm.dao.OrderDetailsMapper;
import com.jiangzhiyan.crm.utils.AssertUtil;
import com.jiangzhiyan.crm.vo.CustomerOrder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JiangZhiyan
 */
@Service
public class CustomerOrderService extends BaseService<CustomerOrder,Integer> {

    @Resource
    private CustomerOrderMapper customerOrderMapper;

    @Resource
    private OrderDetailsMapper orderDetailsMapper;

    /**
     * 查询一个客户全部订单
     * @param query 查询条件
     * @return layui数据表要求格式的Map集合
     */
    @Override
    public Map<String, Object> selectByParams(BaseQuery query) {
        Map<String,Object> map = new HashMap<>(4);
        PageHelper.startPage(query.getPage(),query.getLimit());
        List<CustomerOrder> orders = customerOrderMapper.selectByParams(query);
        for (CustomerOrder order : orders){
            setOrderState(order);
        }
        PageInfo<CustomerOrder> pageInfo = new PageInfo<>(orders);
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    /**
     * 添加客户订单
     * @param order 待添加的订单
     */
    @Transactional(rollbackFor = Exception.class)
    public void addCustomerOrder(CustomerOrder order) {
        checkAddOrUpdateOrderParams(order);
        initAddOrUpdateOrderParams(order);
        Integer result = customerOrderMapper.insertSelective(order);
        AssertUtil.isTrue(result != 1,"服务器异常!订单添加失败...");
    }

    /**
     * 更新客户订单
     * @param order 待更新的订单
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateCustomerOrder(CustomerOrder order) {
        checkAddOrUpdateOrderParams(order);
        initAddOrUpdateOrderParams(order);
        Integer result = customerOrderMapper.updateByPrimaryKeySelective(order);
        AssertUtil.isTrue(result != 1,"服务器异常!订单添加失败...");
    }

    /**
     * 删除客户订单(批量,实际是更新)
     * @param ids 待删除的订单id集合
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteCustomerOrders(List<Integer> ids) {
        AssertUtil.isTrue(ids == null || ids.size() == 0,"无效的请求!请重试...");
        //1.删除详细订单记录
        Integer orderDerailsCount = orderDetailsMapper.selectCountByOrderIds(ids);
        Integer deleteDetailsResult = orderDetailsMapper.deleteByOrderIds(ids);
        AssertUtil.isTrue(!orderDerailsCount.equals(deleteDetailsResult),"服务器异常!删除失败...");
        //2.删除客户订单
        Integer result = customerOrderMapper.deleteBatch(ids);
        AssertUtil.isTrue(result != ids.size(),"服务器异常!删除失败...");
    }
//----------------------------------------分隔线------------------------------------------------------------------

    /**
     * 设置订单的交付状态
     * @param order 订单
     */
    private void setOrderState(CustomerOrder order){
        if (order != null){
            //代表未交付
            if (order.getActualDate() == null){
                if (order.getRequiredDate().compareTo(new Date()) < 0) {
                    order.setState("逾期未交付");
                } else {
                    order.setState("进行中");
                }
            //代表已交付
            }else {
                if (order.getRequiredDate().compareTo(order.getActualDate()) < 0){
                    order.setState("逾期已交付");
                }else{
                    order.setState("按时交付");
                }
            }
        }
    }

    /**
     * 添加/更新订单参数校验
     * @param order 待添加/更新的订单
     */
    private void checkAddOrUpdateOrderParams(CustomerOrder order){
        AssertUtil.isTrue(order == null,"无效的请求!请重试...");
        AssertUtil.isTrue(order.getOrderDate() == null,"下单日期不能为空!");
        AssertUtil.isTrue(order.getRequiredDate() == null,"要求交付日期不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(order.getAddress()),"收货地址不能为空!");
        if (order.getId() == null){
            AssertUtil.isTrue(order.getOrderDate().compareTo(order.getRequiredDate())>0,
                    "要求交付日期不能早于下单日期!");
        }else {
            AssertUtil.isTrue(order.getOrderDate().compareTo(order.getActualDate())>0,
                    "实际交付日期不能早于下单日期!");
        }
    }

    /**
     * 添加/更新订单参数初始化
     * @param order 待添加/更新订单
     */
    private void initAddOrUpdateOrderParams(CustomerOrder order){
        if (order.getId() == null){
            order.setIsValid(1);
            order.setCreateDate(new Date());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            order.setOrderNo(order.getCusId()+sdf.format(new Date()));
        }else {
            order.setUpdateDate(new Date());
        }
    }
}
