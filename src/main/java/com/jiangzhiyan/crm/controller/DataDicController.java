package com.jiangzhiyan.crm.controller;

import com.jiangzhiyan.crm.annotations.OptValue;
import com.jiangzhiyan.crm.base.BaseController;
import com.jiangzhiyan.crm.base.ResultInfo;
import com.jiangzhiyan.crm.query.DataDicQuery;
import com.jiangzhiyan.crm.service.DataDicService;
import com.jiangzhiyan.crm.vo.DataDic;
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
@RequestMapping("/dataDic")
public class DataDicController extends BaseController {

    @Autowired
    private DataDicService dataDicService;

    @OptValue("5040")
    @GetMapping("/index")
    public String index(){
        return "/dataDic/dataDic";
    }

    @OptValue("5040")
    @GetMapping("/list")
    @ResponseBody
    public Map<String,Object> list(DataDicQuery query){
        return dataDicService.selectByParams(query);
    }

    @OptValue({"504001","504002"})
    @GetMapping("/addOrUpdateDataDicPage")
    public String toAddOrUpdateDataDicPage(Integer id, HttpServletRequest request){
        if (id != null){
            DataDic dataDic = dataDicService.selectByPrimaryKey(id);
            request.setAttribute("dataDic",dataDic);
        }
        return "/dataDic/add_update";
    }

    @OptValue("504001")
    @PostMapping("/add")
    @ResponseBody
    public ResultInfo addDataDic(DataDic dataDic){
        dataDicService.addDataDic(dataDic);
        return success("字典数据添加成功!");
    }

    @OptValue("504002")
    @PostMapping("/update")
    @ResponseBody
    public ResultInfo updateDataDic(DataDic dataDic){
        dataDicService.updateDataDic(dataDic);
        return success("字典数据更新成功!");
    }

    @OptValue("504003")
    @PostMapping("/delete")
    @ResponseBody
    public ResultInfo deleteDataDic(@RequestParam("ids") List<Integer> ids){
        dataDicService.deleteDataDic(ids);
        return success("字典数据删除成功!");
    }
}
