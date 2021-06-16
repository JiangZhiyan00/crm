package com.jiangzhiyan.crm.controller;

import com.jiangzhiyan.crm.annotations.OptValue;
import com.jiangzhiyan.crm.base.BaseController;
import com.jiangzhiyan.crm.base.ResultInfo;
import com.jiangzhiyan.crm.enums.ServeState;
import com.jiangzhiyan.crm.query.CustomerServeQuery;
import com.jiangzhiyan.crm.service.CustomerServeService;
import com.jiangzhiyan.crm.utils.CookieUtil;
import com.jiangzhiyan.crm.utils.LoginUserUtil;
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

    @OptValue({"3010","3020","3030","3040","3050"})
    @GetMapping("/list")
    @ResponseBody
    public Map<String,Object> list(CustomerServeQuery query,HttpServletRequest request){
        if (ServeState.ASSIGN.getState().equals(query.getState())){
            query.setAssigner(LoginUserUtil.releaseUserIdFromCookie(request).toString());
        }
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

    @OptValue("302001")
    @GetMapping("/addCustomerServeAssignPage")
    public String toAddCustomerServeAssignPage(Integer id,HttpServletRequest request){
        if (id != null){
            CustomerServe customerServe = customerServeService.selectByPrimaryKey(id);
            request.setAttribute("customerServe",customerServe);
        }
        return "/customerServe/customer_serve_assign_add";
    }

    @OptValue("303001")
    @GetMapping("/addCustomerServeProcePage")
    public String toAddCustomerServeProcePage(Integer id,HttpServletRequest request){
        if (id != null){
            CustomerServe customerServe = customerServeService.selectByPrimaryKey(id);
            request.setAttribute("customerServe",customerServe);
        }
        return "/customerServe/customer_serve_proce_add";
    }


    @OptValue("304001")
    @GetMapping("/addCustomerServeFeedbackPage")
    public String toAddCustomerServeFeedbackPage(Integer id,HttpServletRequest request){
        if (id != null){
            CustomerServe customerServe = customerServeService.selectByPrimaryKey(id);
            request.setAttribute("customerServe",customerServe);
        }
        return "/customerServe/customer_serve_feed_back_add";
    }

    @OptValue("301001")
    @PostMapping("/add")
    @ResponseBody
    public ResultInfo addCustomerServe(CustomerServe customerServe){
        customerServeService.addCustomerServe(customerServe);
        return success("服务添加成功!");
    }

    @OptValue("301002")
    @PostMapping("/updateInCreatePage")
    @ResponseBody
    public ResultInfo updateInCreatePage(CustomerServe customerServe){
        customerServeService.updateServeInCreatePage(customerServe);
        return success("服务更新成功!");
    }

    @OptValue({"302001","303001","304001"})
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
