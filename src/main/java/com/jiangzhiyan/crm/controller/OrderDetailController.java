package com.jiangzhiyan.crm.controller;

import com.jiangzhiyan.crm.annotations.OptValue;
import com.jiangzhiyan.crm.base.BaseController;
import com.jiangzhiyan.crm.base.ResultInfo;
import com.jiangzhiyan.crm.query.OrderDetailsQuery;
import com.jiangzhiyan.crm.service.CustomerOrderService;
import com.jiangzhiyan.crm.service.OrderDetailService;
import com.jiangzhiyan.crm.vo.CustomerOrder;
import com.jiangzhiyan.crm.vo.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orderDetails")
public class OrderDetailController extends BaseController {

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private CustomerOrderService customerOrderService;

    @OptValue("201007")
    @GetMapping("/orderDetailPage")
    public String toOrderDetailPage(Integer orderId, HttpServletRequest request){
        CustomerOrder order = customerOrderService.selectByPrimaryKey(orderId);
        request.setAttribute("order",order);
        return "/customer_order_details/customer_order_detail";
    }

    @OptValue("201007")
    @GetMapping("/list")
    @ResponseBody
    public Map<String,Object> list(OrderDetailsQuery query){
        if (query.getOrderId() == null){
            return null;
        }
        return orderDetailService.selectByParams(query);
    }

    @OptValue("201007")
    @GetMapping("/addOrUpdateOrderDetailsPage")
    public String toAddOrUpdateOrderDetailsPage(Integer id,Integer orderId,HttpServletRequest request){
        if (id != null){
            OrderDetails orderDetails = orderDetailService.selectByPrimaryKey(id);
            request.setAttribute("orderDetails",orderDetails);
        }
        request.setAttribute("orderId",orderId);
        return "/customer_order_details/add_update";
    }

    @OptValue("201007")
    @PostMapping("/add")
    @ResponseBody
    public ResultInfo addOrderDetails(OrderDetails orderDetails){
        orderDetailService.addOrderDetails(orderDetails);
        return success("商品订单添加成功!");
    }

    @OptValue("201007")
    @PostMapping("/update")
    @ResponseBody
    public ResultInfo updateOrderDetails(OrderDetails orderDetails){
        orderDetailService.updateOrderDetails(orderDetails);
        return success("商品订单更新成功!");
    }

    @OptValue("201007")
    @PostMapping("/delete")
    @ResponseBody
    public ResultInfo deleteOrderDetails(@RequestParam("ids") List<Integer> ids){
        orderDetailService.deleteOrderDetails(ids);
        return success("商品订单删除成功!");
    }
}
