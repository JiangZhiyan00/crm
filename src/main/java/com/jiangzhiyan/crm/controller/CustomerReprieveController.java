package com.jiangzhiyan.crm.controller;

import com.jiangzhiyan.crm.annotations.OptValue;
import com.jiangzhiyan.crm.base.BaseController;
import com.jiangzhiyan.crm.base.ResultInfo;
import com.jiangzhiyan.crm.query.CustomerReprieveQuery;
import com.jiangzhiyan.crm.service.CustomerReprieveService;
import com.jiangzhiyan.crm.vo.CustomerReprieve;
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
@RequestMapping("/customerRep")
public class CustomerReprieveController extends BaseController {

    @Autowired
    private CustomerReprieveService customerReprieveService;

    @OptValue("202001")
    @PostMapping("/cancel")
    @ResponseBody
    public ResultInfo cancelReprieve(Integer lossId){
        customerReprieveService.cancelReprieveByLossId(lossId);
        return success("取消暂缓成功!");
    }

    @OptValue("202001")
    @GetMapping("/list")
    @ResponseBody
    public Map<String,Object> list(CustomerReprieveQuery query){
        return customerReprieveService.selectByParams(query);
    }

    @OptValue("202001")
    @GetMapping("/addOrUpdateReprievePage")
    public String toAddOrUpdateReprievePage(Integer lossId,Integer id, HttpServletRequest request){
        request.setAttribute("lossId",lossId);
        if (id != null){
            CustomerReprieve customerRep = customerReprieveService.selectByPrimaryKey(id);
            request.setAttribute("customerRep",customerRep);
        }
        return "/customerLoss/customer_rep_add_update";
    }

    @OptValue("202001")
    @PostMapping("/firstAdd")
    @ResponseBody
    public ResultInfo firstAddReprieve(CustomerReprieve customerReprieve){
        customerReprieveService.firstAddReprieve(customerReprieve);
        return success("添加暂缓成功!");
    }

    @OptValue("202001")
    @PostMapping("/add")
    @ResponseBody
    public ResultInfo addReprieve(CustomerReprieve customerReprieve){
        customerReprieveService.addReprieve(customerReprieve);
        return success("添加暂缓措施成功!");
    }

    @OptValue("202001")
    @PostMapping("/update")
    @ResponseBody
    public ResultInfo updateReprieve(CustomerReprieve customerReprieve){
        customerReprieveService.updateReprieve(customerReprieve);
        return success("更新暂缓措施成功!");
    }

    @OptValue("202001")
    @PostMapping("/delete")
    @ResponseBody
    public ResultInfo deleteReprieves(@RequestParam("ids") List<Integer> ids){
        customerReprieveService.deleteReprieves(ids);
        return success("删除成功!");
    }
}
