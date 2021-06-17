package com.jiangzhiyan.crm.controller;

import com.jiangzhiyan.crm.annotations.OptValue;
import com.jiangzhiyan.crm.base.BaseController;
import com.jiangzhiyan.crm.query.CustomerContributionQuery;
import com.jiangzhiyan.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author JiangZhiyan
 */
@Controller
@RequestMapping("/report")
public class ReportController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @OptValue("40")
    @GetMapping("/{path}")
    public String toReportPage(@PathVariable("path") String path){
        return "/report/customer_"+path;
    }

    @OptValue("4010")
    @GetMapping("/queryContributionByParams")
    @ResponseBody
    public Map<String,Object> queryContributions(CustomerContributionQuery query){
        return customerService.queryContribByParams(query);
    }

    @OptValue("4010")
    @GetMapping("/customerContrib")
    @ResponseBody
    public List<Map<String,Object>> getCustomerContrib(CustomerContributionQuery query){
        return customerService.getCustomerContrib(query);
    }

    @OptValue("4020")
    @GetMapping("/countCustomerLevelForHistogram")
    @ResponseBody
    public Map<String,Object> countCustomerLevelForHistogram(){
        return customerService.countCustomerLevelForHistogram();
    }

    @OptValue("4020")
    @GetMapping("/countCustomerLevelForPie")
    @ResponseBody
    public List<Map<String,Object>> countCustomerLevelForPie(){
        return customerService.countCustomerLevelForPie();
    }

    @OptValue("4030")
    @GetMapping("/getServeTypeForPie")
    @ResponseBody
    public List<Map<String,Object>> getServeTypeForPie(){
        return customerService.getServeTypeForPie();
    }

    @OptValue("4030")
    @GetMapping("/getSatisfactionForPie")
    @ResponseBody
    public List<Map<String,Object>> getSatisfactionForPie(){
        return customerService.getSatisfactionForPie();
    }

    @OptValue("4040")
    @GetMapping("/getLossCustomerForPie")
    @ResponseBody
    public List<Map<String,Object>> getLossCustomerForPie(){
        return customerService.getLossCustomerForPie();
    }
}
