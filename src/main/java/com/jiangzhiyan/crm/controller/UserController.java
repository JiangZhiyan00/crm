package com.jiangzhiyan.crm.controller;

import com.jiangzhiyan.crm.annotations.OptValue;
import com.jiangzhiyan.crm.base.BaseController;
import com.jiangzhiyan.crm.base.ResultInfo;
import com.jiangzhiyan.crm.query.UserQuery;
import com.jiangzhiyan.crm.service.UserService;
import com.jiangzhiyan.crm.utils.AssertUtil;
import com.jiangzhiyan.crm.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 用户模块
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param userName
     * @param userPassword
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public ResultInfo userLogin(String userName,String userPassword){

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setResult(userService.userLogin(userName,userPassword));
        return resultInfo;
    }

    @RequestMapping("/toPasswordPage")
    public String toPasswordPage(HttpServletRequest request){
        return "/user/updatePassword";
    }

    /**
     * 更改密码
     * @param request
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    @PostMapping("/updatePassword")
    @ResponseBody
    public ResultInfo updatePassword(HttpServletRequest request, String oldPassword,
                                     String newPassword, String confirmPassword){

        ResultInfo resultInfo = new ResultInfo();
        userService.updateUserPassword(request,oldPassword,newPassword,confirmPassword);
        return resultInfo;
    }

    /**
     * 查询所有销售
     * @return
     */
    @OptValue({"101002","101004"})
    @RequestMapping("/selectAllSales")
    @ResponseBody
    public List<Map<String,Object>> selectAllSales(){
        return userService.selectAllSales();
    }

    /**
     * 打开用户管理界面
     * @return
     */
    @OptValue("5010")
    @GetMapping("/index")
    public String toUserManagePage(){
        return "/user/user";
    }

    /**
     * 条件查询用户信息
     * @param query
     * @return
     */
    @OptValue("501002")
    @GetMapping("/queryUsers")
    @ResponseBody
    public Map<String,Object> queryUsers(UserQuery query){
        return userService.selectByParams(query);
    }

    /**
     * 打开添加或修改用户信息窗口
     * @return
     */
    @OptValue({"501001","501003"})
    @RequestMapping("addOrUpdateUserPage")
    public String toAddOrUpdateUserPage(Integer id,HttpServletRequest request){
        if (id != null){
            User theUser = userService.selectByPrimaryKey(id);
            AssertUtil.isTrue(theUser == null,"不存在此用户!");
            request.setAttribute("theUser",theUser);
        }
        return "/user/add_update";
    }

    /**
     * 添加新用户
     * @param user
     * @return
     */
    @OptValue("501001")
    @PostMapping("/addUser")
    @ResponseBody
    public ResultInfo addUser(User user){
        userService.addUser(user);
        return success("新用户添加成功!");
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @OptValue("501003")
    @PostMapping("/updateUser")
    @ResponseBody
    public ResultInfo updateUser(User user){
        userService.updateUser(user);
        return success("用户信息更新成功!");
    }

    /**
     * 删除用户(单个/批量)
     * @param ids
     * @return
     */
    @OptValue("501004")
    @PostMapping("/delete")
    @ResponseBody
    public ResultInfo deleteUsers(@RequestParam("ids") List<Integer> ids){
        userService.deleteUsersByIds(ids);
        return success("用户删除成功!");
    }
}
