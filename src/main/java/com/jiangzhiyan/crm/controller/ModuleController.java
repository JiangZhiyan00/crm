package com.jiangzhiyan.crm.controller;

import com.jiangzhiyan.crm.base.BaseController;
import com.jiangzhiyan.crm.model.ZTreeModel;
import com.jiangzhiyan.crm.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/module")
public class ModuleController extends BaseController {

    @Autowired
    private ModuleService moduleService;

   /**
    * 查询所有资源信息,返回z-tree插件要求数据格式
    * @return json like [{id:2,pId:1,name:"moduleName"},...]
    */
    @GetMapping("/selectAllModules")
    @ResponseBody
    public List<ZTreeModel> selectAllModules(Integer roleId){
        return moduleService.selectAllModules(roleId);
    }
}
