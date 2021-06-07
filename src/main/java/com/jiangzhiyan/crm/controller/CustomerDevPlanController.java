package com.jiangzhiyan.crm.controller;

import com.jiangzhiyan.crm.base.BaseController;
import com.jiangzhiyan.crm.base.ResultInfo;
import com.jiangzhiyan.crm.enums.StateStatus;
import com.jiangzhiyan.crm.query.DevPlanQuery;
import com.jiangzhiyan.crm.query.SaleChanceQuery;
import com.jiangzhiyan.crm.service.CustomerDevPlanService;
import com.jiangzhiyan.crm.service.SaleChanceService;
import com.jiangzhiyan.crm.utils.AssertUtil;
import com.jiangzhiyan.crm.utils.LoginUserUtil;
import com.jiangzhiyan.crm.vo.CustomerDevPlan;
import com.jiangzhiyan.crm.vo.SaleChance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/cusDevPlan")
public class CustomerDevPlanController extends BaseController {

    @Autowired
    private CustomerDevPlanService customerDevPlanService;

    @Autowired
    private SaleChanceService saleChanceService;

    /**
     * 客户开发计划主页
     * @return
     */
    @RequestMapping("/index")
    public String toSaleChanceIndex(){
        return "/cusDevPlan/cus_dev_plan";
    }

    /**
     * 多条件查询当前登录用户的营销机会
     * @param query
     * @param request
     * @return
     */
    @RequestMapping("/queryOwnSaleChances")
    @ResponseBody
    public Map<String, Object> queryOwnSaleChances(SaleChanceQuery query, HttpServletRequest request){
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        query.setState(StateStatus.STATED.getStatus());
        if (userId != null){
            query.setAssignMan(userId);
        }
        return saleChanceService.selectByParams(query);
    }

    /**
     * 跳转至详情或维护页面
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/toCusDevPlanDataPage")
    public String toCusDevPlanDataPage(Integer id,HttpServletRequest request){
        SaleChance saleChance = saleChanceService.selectByPrimaryKey(id);
        request.setAttribute("saleChance",saleChance);
        return "/cusDevPlan/cus_dev_plan_data";
    }

    /**
     * 查询某一营销机会的所有有效计划
     * @param query
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(DevPlanQuery query){
        AssertUtil.isTrue(query == null || query.getSaleChanceId() == null,"数据不存在!");
        return customerDevPlanService.selectByParams(query);
    }

    /**
     * 跳转到添加/更新页面
     * @param saleChanceId
     * @param request
     * @return
     */
    @RequestMapping("/addOrUpdateCusDevPlanPage")
    public String toAddOrUpdatePlanPage(Integer saleChanceId,Integer id,
                                        HttpServletRequest request){
        if (saleChanceId != null){
            request.setAttribute("saleChanceId",saleChanceId);
            if (id != null){
                CustomerDevPlan cusDevPlan = customerDevPlanService.selectByPrimaryKey(id);
                request.setAttribute("cusDevPlan",cusDevPlan);
            }
        }
        return "/cusDevPlan/add_update";
    }
    /**
     * 添加开发计划
     * @param plan
     * @return
     */
    @RequestMapping("/addPlan")
    @ResponseBody
    public ResultInfo addDevPlan(CustomerDevPlan plan){
        customerDevPlanService.insertSelective(plan);
        return success("添加成功!");
    }

    /**
     * 更新开发计划
     * @param plan
     * @return
     */
    @RequestMapping("/updatePlan")
    @ResponseBody
    public ResultInfo updatePlan(CustomerDevPlan plan){
        customerDevPlanService.updateByPrimaryKeySelective(plan);
        return success("更新成功!");
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultInfo deletePlan(Integer id){
        customerDevPlanService.deletePlanById(id);
        return success("删除成功!");
    }
}
