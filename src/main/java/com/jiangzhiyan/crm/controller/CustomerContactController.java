package com.jiangzhiyan.crm.controller;

import com.jiangzhiyan.crm.annotations.OptValue;
import com.jiangzhiyan.crm.base.BaseController;
import com.jiangzhiyan.crm.base.ResultInfo;
import com.jiangzhiyan.crm.query.CustomerContactQuery;
import com.jiangzhiyan.crm.service.CustomerContactService;
import com.jiangzhiyan.crm.vo.CustomerContact;
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
@RequestMapping("/customerContact")
public class CustomerContactController extends BaseController {

    @Autowired
    private CustomerContactService customerContactService;

    @OptValue("201006")
    @GetMapping("/customerContactPage")
    public String customerContactPage(Integer cusId, HttpServletRequest request){
        request.setAttribute("cusId",cusId);
        return "/customerContact/customer_contact";
    }

    @OptValue("201006")
    @GetMapping("/list")
    @ResponseBody
    public Map<String,Object> list(CustomerContactQuery query){
        if (query.getCusId() == null){
            return null;
        }
        return customerContactService.selectByParams(query);
    }

    @OptValue("201006")
    @GetMapping("/addOrUpdateCustomerContactPage")
    public String addOrUpdateCustomerContactPage(Integer id,Integer cusId,
                                                 HttpServletRequest request){
        request.setAttribute("cusId",cusId);
        if (id != null){
            CustomerContact customerContact = customerContactService.selectByPrimaryKey(id);
            request.setAttribute("customerContact",customerContact);
        }
        return "/customerContact/add_update";
    }

    @OptValue("201006")
    @PostMapping("/add")
    @ResponseBody
    public ResultInfo addCustomerContact(CustomerContact customerContact){
        customerContactService.addCustomerContact(customerContact);
        return success("交流记录添加成功!");
    }

    @OptValue("201006")
    @PostMapping("/update")
    @ResponseBody
    public ResultInfo updateCustomerContact(CustomerContact customerContact){
        customerContactService.updateCustomerContact(customerContact);
        return success("交流记录更新成功!");
    }

    @OptValue("201006")
    @PostMapping("/delete")
    @ResponseBody
    public ResultInfo deleteCustomerContacts(@RequestParam("ids") List<Integer> ids){
        customerContactService.deleteCustomerContacts(ids);
        return success("交流记录删除成功");
    }
}
