package com.jiangzhiyan.crm.interceptor;

import com.jiangzhiyan.crm.exceptions.UnLoginException;
import com.jiangzhiyan.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author JiangZhiyan
 */
@Component
public class UnLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        if (userId < 0){
            throw new UnLoginException();
        }
        return true;
    }
}
