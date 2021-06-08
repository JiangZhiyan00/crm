package com.jiangzhiyan.crm.controller;

import com.jiangzhiyan.crm.annotations.OptValue;
import com.jiangzhiyan.crm.base.BaseController;
import com.jiangzhiyan.crm.base.ResultInfo;
import com.jiangzhiyan.crm.query.RoleQuery;
import com.jiangzhiyan.crm.service.RoleService;
import com.jiangzhiyan.crm.vo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询所有职位
     * @return
     */
    @OptValue("501001")
    @GetMapping("/selectAllRoles")
    @ResponseBody
    public List<Map<String,Object>> selectAllRoles(){
        return roleService.selectAllRoles();
    }

    /**
     * 获取待编辑信息用户的职位
     * @param userId
     * @return
     */
    @OptValue("501003")
    @GetMapping("/selectTheUserRoles")
    @ResponseBody
    public List<Map<String,Object>> selectTheUserRoles(Integer userId){
        return roleService.selectRolesByUserId(userId);
    }

    /**
     * 打开职位主页
     * @return
     */
    @OptValue("5020")
    @GetMapping("/index")
    public String roleIndex(){
        return "/role/role";
    }

    /**
     * 分页查询所有职位信息
     * @param query
     * @return
     */
    @OptValue("502002")
    @GetMapping("/list")
    @ResponseBody
    public Map<String,Object> selectByParams(RoleQuery query){
        return roleService.selectByParams(query);
    }

    /**
     * 跳转到添加/更新职位页面
     * @param id
     * @return
     */
    @OptValue({"502001","502003"})
    @GetMapping("/addOrUpdateRolePage")
    public String toAddOrUpdateRolePage(Integer id, HttpServletRequest request){
        if (id != null){
            Role role = roleService.selectByPrimaryKey(id);
            request.setAttribute("role",role);
        }
        return "/role/add_update";
    }

    /**
     * 添加职位信息
     * @param role
     * @return
     */
    @OptValue("502001")
    @PostMapping("/addRole")
    @ResponseBody
    public ResultInfo addRole(Role role){
        roleService.addRole(role);
        return success("职位信息添加成功!");
    }

    /**
     * 更新职位信息
     * @param role
     * @return
     */
    @OptValue("502003")
    @PostMapping("/updateRole")
    @ResponseBody
    public ResultInfo updateRole(Role role){
        roleService.updateRole(role);
        return success("职位信息更新成功!");
    }

    /**
     * 删除职位信息(批量)
     * @param ids
     * @return
     */
    @OptValue("502004")
    @PostMapping("/deleteRoles")
    @ResponseBody
    public ResultInfo deleteRoles(@RequestParam("ids") List<Integer> ids){
        roleService.deleteRoles(ids);
        return success("职位信息删除成功!");
    }

    /**
     * 打开授权页面
     * @param roleId
     * @param request
     * @return
     */
    @OptValue("502005")
    @GetMapping("/toAddGrantPage")
    public String toAddGrantPage(Integer roleId,HttpServletRequest request){
        request.setAttribute("roleId",roleId);
        return "/role/grant";
    }

    /**
     * 职位授权
     * @param moduleIds
     * @param roleId
     * @return
     */
    @OptValue("502005")
    @PostMapping("addGrant")
    @ResponseBody
    public ResultInfo addGrant(@RequestParam("moduleIds") List<Integer> moduleIds,
                               @RequestParam("roleId")Integer roleId){
        roleService.addGrant(moduleIds,roleId);
        return success("授权成功!");
    }
}
