package com.jiangzhiyan.crm.controller;

import com.jiangzhiyan.crm.annotations.OptValue;
import com.jiangzhiyan.crm.base.BaseController;
import com.jiangzhiyan.crm.base.ResultInfo;
import com.jiangzhiyan.crm.query.CustomerServeQuery;
import com.jiangzhiyan.crm.service.CustomerServeService;
import com.jiangzhiyan.crm.vo.CustomerServe;
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
@RequestMapping("/customerServe")
public class CustomerServeController extends BaseController {

    @Autowired
    private CustomerServeService customerServeService;

    @OptValue("3010")
    @GetMapping("/index/1")
    public String customerServeIndex(){
        return "/customerServe/customer_serve";
    }

    @OptValue("3020")
    @GetMapping("/index/2")
    public String customerServeAssignIndex(){
        return "/customerServe/customer_serve_assign";
    }

    @OptValue("3030")
    @GetMapping("/index/3")
    public String customerServeProce(){
        return "/customerServe/customer_serve_proce";
    }

    @OptValue("3040")
    @GetMapping("/index/4")
    public String customerServeFeedBack(){
        return "/customerServe/customer_serve_feed_back";
    }

    @OptValue("3050")
    @GetMapping("/index/5")
    public String customerServeArchive(){
        return "/customerServe/customer_serve_archive";
    }

    @OptValue("3010")
    @GetMapping("/list")
    @ResponseBody
    public Map<String,Object> list(CustomerServeQuery query){
        return customerServeService.selectByParams(query);
    }

    @OptValue({"301001","301002"})
    @GetMapping("/addOrUpdateCustomerServePage")
    public String toAddOrUpdateCustomerServePage(Integer serveId, HttpServletRequest request){
        if (serveId != null){
            CustomerServe customerServe = customerServeService.selectByPrimaryKey(serveId);
            request.setAttribute("customerServe",customerServe);
        }
        return "/customerServe/customer_serve_add_update";
    }

    @OptValue("301001")
    @PostMapping("/add")
    @ResponseBody
    public ResultInfo addCustomerServe(CustomerServe customerServe){
        customerServeService.addCustomerServe(customerServe);
        return success("服务添加成功!");
    }

    @OptValue({"301002","302001","303001","304001"})
    @PostMapping("/update")
    @ResponseBody
    public ResultInfo updateCustomerServe(CustomerServe customerServe){
        return success(customerServeService.updateCustomerServe(customerServe));
    }

    @OptValue("301003")
    @PostMapping("/delete")
    @ResponseBody
    public ResultInfo deleteCustomerServes(@RequestParam("ids")List<Integer> ids){
        customerServeService.deleteCustomerServes(ids);
        return success("服务删除成功!");
    }
}
