package com.jiangzhiyan.crm.controller;

import com.jiangzhiyan.crm.annotations.OptValue;
import com.jiangzhiyan.crm.base.BaseController;
import com.jiangzhiyan.crm.query.CustomerLossQuery;
import com.jiangzhiyan.crm.service.CustomerLossService;
import com.jiangzhiyan.crm.vo.CustomerLoss;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author JiangZhiyan
 */
@Controller
@RequestMapping("/customerLoss")
public class CustomerLossController extends BaseController {

    @Resource
    private CustomerLossService customerLossService;

    @OptValue("2020")
    @GetMapping("/index")
    public String index(){
        return "/customerLoss/customer_loss";
    }

    @OptValue("2020")
    @GetMapping("/list")
    @ResponseBody
    public Map<String,Object> list(CustomerLossQuery query){
        return customerLossService.selectByParams(query);
    }

    @OptValue("202001")
    @GetMapping("/addReprievePage")
    public String toAddReprievePage(Integer lossId, HttpServletRequest request){
        request.setAttribute("lossId",lossId);
        return "/customerLoss/customer_rep_firstAdd";
    }

    @OptValue("202001")
    @GetMapping("/customerReprievePage")
    public String toReprievePage(Integer id, HttpServletRequest request){
        CustomerLoss customerLoss = customerLossService.selectByPrimaryKey(id);
        request.setAttribute("customerLoss",customerLoss);
        return "/customerLoss/customer_rep";
    }
}
