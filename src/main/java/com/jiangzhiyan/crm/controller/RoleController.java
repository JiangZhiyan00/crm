package com.jiangzhiyan.crm.controller;

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
    @GetMapping("/selectTheUserRoles")
    @ResponseBody
    public List<Map<String,Object>> selectTheUserRoles(Integer userId){
        return roleService.selectRolesByUserId(userId);
    }

    /**
     * 打开职位主页
     * @return
     */
    @GetMapping("/index")
    public String roleIndex(){
        return "/role/role";
    }

    /**
     * 分页查询所有职位信息
     * @param query
     * @return
     */
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
    @PostMapping("addGrant")
    @ResponseBody
    public ResultInfo addGrant(@RequestParam("moduleIds") List<Integer> moduleIds,
                               @RequestParam("roleId")Integer roleId){
        roleService.addGrant(moduleIds,roleId);
        return success("授权成功!");
    }
}
