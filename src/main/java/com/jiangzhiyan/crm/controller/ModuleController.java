package com.jiangzhiyan.crm.controller;

import com.jiangzhiyan.crm.annotations.OptValue;
import com.jiangzhiyan.crm.base.BaseController;
import com.jiangzhiyan.crm.base.ResultInfo;
import com.jiangzhiyan.crm.model.ZTreeModel;
import com.jiangzhiyan.crm.service.ModuleService;
import com.jiangzhiyan.crm.vo.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/module")
public class ModuleController extends BaseController {

    @Autowired
    private ModuleService moduleService;

   /**
    * 查询所有资源信息,返回z-tree插件要求数据格式
    * @return json like [{id:2,pId:1,name:"moduleName"},...]
    */
    @OptValue("502005")
    @GetMapping("/selectAllModules")
    @ResponseBody
    public List<ZTreeModel> selectAllModules(Integer roleId){
        return moduleService.selectAllModules(roleId);
    }

    /**
     * 跳转到资源管理页面
     * @return 资源管理页面
     */
    @OptValue("5030")
    @GetMapping("/index")
    public String toModuleIndex(){
        return "/module/module";
    }

    /**
     * 查询所有有效资源
     * @return layui数据表要求格式的Map
     */
    @OptValue("503002")
    @GetMapping("/list")
    @ResponseBody
    public Map<String,Object> queryAllModules(){
        return moduleService.queryAllModules();
    }

    /**
     * 跳转到添加主目录页面
     * @param grade
     * @param parentId
     * @param request
     * @return
     */
    @OptValue("503001")
    @GetMapping("/toAddModulePage")
    public String toAddModulePage(Integer grade, Integer parentId, HttpServletRequest request){
        request.setAttribute("grade",grade);
        request.setAttribute("parentId",parentId);
        return "/module/add";
    }

    /**
     * 添加资源
     * @param module
     * @return
     */
    @OptValue("503001")
    @PostMapping("/addModule")
    @ResponseBody
    public ResultInfo addModule(Module module){
        moduleService.addModule(module);
        return success("添加成功!");
    }

    /**
     * 跳转到资源更新页面
     * @param id
     * @param request
     * @return
     */
    @OptValue("503003")
    @GetMapping("/toUpdateModulePage")
    public String toUpdateModulePage(Integer id,HttpServletRequest request){
        if (id != null){
            Module module = moduleService.selectByPrimaryKey(id);
            request.setAttribute("module",module);
        }
        return "/module/update";
    }

    /**
     * 更新资源信息
     * @param module
     * @return
     */
    @OptValue("503003")
    @PostMapping("/updateModule")
    @ResponseBody
    public ResultInfo updateModule(Module module){
        moduleService.updateModule(module);
        return success("更新成功!");
    }

    /**
     * 删除指定资源
     * @param id
     * @return
     */
    @OptValue("503004")
    @PostMapping("/deleteModule")
    @ResponseBody
    public ResultInfo deleteModule(Integer id){
        moduleService.deleteModule(id);
        return success("删除成功!");
    }
}
