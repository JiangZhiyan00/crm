package com.jiangzhiyan.crm.controller;

import com.jiangzhiyan.crm.base.BaseController;
import com.jiangzhiyan.crm.base.ResultInfo;
import com.jiangzhiyan.crm.query.SaleChanceQuery;
import com.jiangzhiyan.crm.service.SaleChanceService;
import com.jiangzhiyan.crm.utils.CookieUtil;
import com.jiangzhiyan.crm.vo.SaleChance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 销售机会模块
 * @author JiangZhiyan
 */
@Controller
@RequestMapping("/saleChance")
public class SaleChanceController extends BaseController {

    @Autowired
    private SaleChanceService saleChanceService;

    /**
     * 营销机会管理主页
     * @return
     */
    @RequestMapping("/index")
    public String toSaleChanceIndex(){
        return "/saleChance/sale_chance";
    }

    /**
     * 营销机会查询(分页多条件)
     * @param query
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> selectSaleChancesByParams(SaleChanceQuery query){
        return saleChanceService.selectByParams(query);
    }

    /**
     * 添加或更新营销机会页面
     * @return
     */
    @RequestMapping("/toAddOrUpdatePage")
    public String toAddOrUpdatePage(Integer id, HttpServletRequest request){
        //修改
        if (id != null){
            SaleChance saleChance = saleChanceService.selectByPrimaryKey(id);
            request.setAttribute("saleChance",saleChance);
        }else {
            //添加
            String userTrueName = CookieUtil.getCookieValue(request,"trueName");
            SaleChance saleChance = new SaleChance();
            saleChance.setCreateMan(userTrueName);
            request.setAttribute("saleChance",saleChance);
        }
        return "/saleChance/add_update";
    }

    /**
     * 添加销售机会
     * @param saleChance
     * @return
     */
    @RequestMapping("/addSaleChance")
    @ResponseBody
    public ResultInfo addSaleChance(SaleChance saleChance){
        return saleChanceService.addSaleChance(saleChance);
    }

    /**
     * 更新销售机会
     * @param saleChance
     * @return
     */
    @RequestMapping(value = "/updateSaleChance")
    @ResponseBody
    public ResultInfo updateSaleChance(SaleChance saleChance){
        return saleChanceService.updateSaleChance(saleChance);
    }

    /**
     * 删除营销机会
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public ResultInfo deleteBatch(@RequestParam("ids") List<Integer> ids){
        saleChanceService.deleteBatch(ids);
        return success("删除成功!");
    }

    /**
     * 更改开发状态
     * @param saleChance
     * @return
     */
    @RequestMapping("/updateSaleChanceDevResult")
    @ResponseBody
    public ResultInfo updateSaleChanceDevResult(SaleChance saleChance){
        saleChanceService.updateDevResult(saleChance);
        return success("更改成功!");
    }
}
