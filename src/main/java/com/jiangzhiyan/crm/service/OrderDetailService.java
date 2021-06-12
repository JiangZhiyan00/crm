package com.jiangzhiyan.crm.service;

import com.jiangzhiyan.crm.base.BaseService;
import com.jiangzhiyan.crm.dao.OrderDetailsMapper;
import com.jiangzhiyan.crm.utils.AssertUtil;
import com.jiangzhiyan.crm.vo.OrderDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author JiangZhiyan
 */
@Service
public class OrderDetailService extends BaseService<OrderDetails,Integer> {

    @Resource
    private OrderDetailsMapper orderDetailsMapper;

    /**
     * 添加商品订单
     * @param orderDetails 待添加的商品订单
     */
    @Transactional(rollbackFor = Exception.class)
    public void addOrderDetails(OrderDetails orderDetails) {
        checkAddOrUpdateParams(orderDetails);
        initAddOrUpdateParams(orderDetails);
        Integer result = orderDetailsMapper.insertSelective(orderDetails);
        AssertUtil.isTrue(result != 1,"服务器异常!请重试...");
    }

    /**
     * 更新商品订单
     * @param orderDetails 待更新的商品订单
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateOrderDetails(OrderDetails orderDetails) {
        checkAddOrUpdateParams(orderDetails);
        initAddOrUpdateParams(orderDetails);
        Integer result = orderDetailsMapper.updateByPrimaryKeySelective(orderDetails);
        AssertUtil.isTrue(result != 1,"服务器异常!请重试...");
    }

    /**
     * 删除商品订单(批量,实际是更新操作)
     * @param ids 待删除的商品订单id集合
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteOrderDetails(List<Integer> ids) {
        AssertUtil.isTrue(ids == null || ids.size() == 0,"无效的请求!请重试...");
        Integer result = orderDetailsMapper.deleteBatch(ids);
        AssertUtil.isTrue(result != ids.size(),"服务器异常!删除商品订单失败...");
    }

//----------------------------------------分隔线------------------------------------------------------------------

    /**
     * 添加/更新商品订单参数校验
     * @param orderDetails 待添加/更新的商品订单
     */
    private void checkAddOrUpdateParams(OrderDetails orderDetails) {
        AssertUtil.isTrue(orderDetails == null,"无效的请求!请重试...");
        AssertUtil.isTrue(StringUtils.isBlank(orderDetails.getGoodsName()),"商品名称不能为空!");
        AssertUtil.isTrue(orderDetails.getGoodsNum() == null, "订购数量不能为空!");
        AssertUtil.isTrue(orderDetails.getGoodsNum() <= 0,"订购数量必须大于0!");
        AssertUtil.isTrue(StringUtils.isBlank(orderDetails.getUnit()),"数量单位不能为空!");
        AssertUtil.isTrue(orderDetails.getPrice() == null,"商品单价不能为空!");
        AssertUtil.isTrue(orderDetails.getPrice().compareTo(BigDecimal.ZERO) <= 0,
                "商品单价必须大于0!");
    }

    /**
     * 添加/更新商品订单参数初始化
     * @param orderDetails 待添加/更新的商品订单
     */
    private void initAddOrUpdateParams(OrderDetails orderDetails) {
        BigDecimal num = new BigDecimal(orderDetails.getGoodsNum().toString());
        orderDetails.setSum(orderDetails.getPrice().multiply(num));
        if (orderDetails.getId() == null){
            orderDetails.setIsValid(1);
            orderDetails.setCreateDate(new Date());
        }else {
            orderDetails.setUpdateDate(new Date());
        }
    }
}
