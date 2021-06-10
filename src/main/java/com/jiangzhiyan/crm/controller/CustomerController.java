package com.jiangzhiyan.crm.controller;

import com.jiangzhiyan.crm.annotations.OptValue;
import com.jiangzhiyan.crm.base.BaseController;
import com.jiangzhiyan.crm.base.ResultInfo;
import com.jiangzhiyan.crm.query.CustomerQuery;
import com.jiangzhiyan.crm.service.CustomerService;
import com.jiangzhiyan.crm.vo.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 客户信息管理模块
 * @author JiangZhiyan
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @OptValue("2010")
    @GetMapping("/index")
    public String customerIndex(){
        return "/customer/customer";
    }

    @OptValue("201004")
    @GetMapping("/list")
    @ResponseBody
    public Map<String,Object> list(CustomerQuery query){
        return customerService.selectByParams(query);
    }

    @OptValue({"201001","201002"})
    @GetMapping("/addOrUpdateCustomerPage")
    public String toAddOrUpdateCustomerPage(Integer id, HttpServletRequest request){
        if (id != null){
            Customer customer = customerService.selectByPrimaryKey(id);
            request.setAttribute("customer",customer);
        }
        return "/customer/add_update";
    }

    @OptValue("201001")
    @PostMapping("/add")
    @ResponseBody
    public ResultInfo addCustomer(Customer customer){
        customerService.addCustomer(customer);
        return success("客户信息添加成功!");
    }

    @OptValue("201002")
    @PostMapping("/update")
    @ResponseBody
    public ResultInfo updateCustomer(Customer customer){
        customerService.updateCustomer(customer);
        return success("客户信息更新成功!");
    }

    @OptValue("201003")
    @PostMapping("/delete")
    @ResponseBody
    public ResultInfo deleteCustomersByIds(@RequestParam("ids") List<Integer> ids){
        customerService.deleteCustomersByIds(ids);
        return success("客户信息删除成功!");
    }
}
