package com.jiangzhiyan.crm.controller;

import com.jiangzhiyan.crm.base.BaseController;
import com.jiangzhiyan.crm.service.UserService;
import com.jiangzhiyan.crm.utils.LoginUserUtil;
import com.jiangzhiyan.crm.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 系统登录页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 系统欢迎页
     * @return
     */
    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    /**
     * 登录用户进入主页,验证权限后展示main页面
     * @return
     */
    @RequestMapping("/main")
    public String main(HttpServletRequest request,HttpSession session){

        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        List<String> optValues = userService.getLoginUserModules(userId);
        User user = userService.selectByPrimaryKey(userId);
        session.setAttribute("user",user);
        session.setAttribute("optValues",optValues);
        return "main";
    }
}
