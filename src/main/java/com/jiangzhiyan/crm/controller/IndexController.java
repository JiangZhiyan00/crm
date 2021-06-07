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

@Controller
public class IndexController extends BaseController {

    @Autowired
    UserService userService;

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
     * 主页
     * @return
     */
    @RequestMapping("/main")
    public String main(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user")!=null){
            return "main";
        }else {
            Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
            User user = userService.selectByPrimaryKey(userId);
            request.getSession().setAttribute("user",user);
        }
        return "main";
    }
}
