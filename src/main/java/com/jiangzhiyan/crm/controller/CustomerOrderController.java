package com.jiangzhiyan.crm.controller;

import com.jiangzhiyan.crm.annotations.OptValue;
import com.jiangzhiyan.crm.base.BaseController;
import com.jiangzhiyan.crm.base.ResultInfo;
import com.jiangzhiyan.crm.query.CustomerContactQuery;
import com.jiangzhiyan.crm.query.CustomerOrderQuery;
import com.jiangzhiyan.crm.service.CustomerLinkManService;
import com.jiangzhiyan.crm.service.CustomerOrderService;
import com.jiangzhiyan.crm.service.CustomerService;
import com.jiangzhiyan.crm.vo.Customer;
import com.jiangzhiyan.crm.vo.CustomerLinkMan;
import com.jiangzhiyan.crm.vo.CustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author JiangZhiyan
 */
@Controller
@RequestMapping("/customerOrder")
public class CustomerOrderController extends BaseController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerLinkManService customerLinkManService;

    @OptValue("201007")
    @GetMapping("/orderInfoPage")
    public String toOrderInfoPage(Integer cusId, HttpServletRequest request){
        Customer customer = customerService.selectByPrimaryKey(cusId);
        CustomerLinkMan customerLinkMan = customerLinkManService.selectByCusId(cusId);
        request.setAttribute("customer",customer);
        request.setAttribute("customerLinkMan",customerLinkMan);
        return "/customerOrder/customer_order";
    }

    @OptValue("201007")
    @GetMapping("/list")
    @ResponseBody
    public Map<String,Object> list(CustomerOrderQuery query){
        return customerOrderService.selectByParams(query);
    }

    @OptValue("201007")
    @GetMapping("/addOrUpdateCustomerOrderPage")
    public String toAddOrUpdateOrderPage(Integer cusId, Integer id,HttpServletRequest request){
        if (id != null){
            CustomerOrder customerOrder = customerOrderService.selectByPrimaryKey(id);
            request.setAttribute("customerOrder",customerOrder);
        }
        request.setAttribute("cusId",cusId);
        return "/customerOrder/add_update";
    }


    @OptValue("201007")
    @PostMapping("/add")
    @ResponseBody
    public ResultInfo addCustomerOrder(CustomerOrder order){
        customerOrderService.addCustomerOrder(order);
        return success("订单添加成功!");
    }

    @OptValue("201007")
    @PostMapping("/update")
    @ResponseBody
    public ResultInfo updateCustomerOrder(CustomerOrder order){
        customerOrderService.updateCustomerOrder(order);
        return success("订单更新成功!");
    }

    @OptValue("201007")
    @PostMapping("/delete")
    @ResponseBody
    public ResultInfo deleteCustomerOrders(@RequestParam("ids") List<Integer> ids){
        customerOrderService.deleteCustomerOrders(ids);
        return success("订单删除成功!");
    }
}
