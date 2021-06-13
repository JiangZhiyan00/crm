package com.jiangzhiyan.crm.controller;

import com.jiangzhiyan.crm.annotations.OptValue;
import com.jiangzhiyan.crm.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customerLoss")
public class CustomerLossController extends BaseController {



    @OptValue("2020")
    @GetMapping("/index")
    public String index(){
        return "/customerLoss/customer_loss";
    }
}
