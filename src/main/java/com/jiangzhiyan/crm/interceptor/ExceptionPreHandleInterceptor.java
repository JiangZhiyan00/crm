package com.jiangzhiyan.crm.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 判断Controller是返回json还是页面的拦截器，为统一异常处理返回结果做判断依据
 * @author JiangZhiyan
 */
@Component("exceptionPreHandleInterceptor")
public class ExceptionPreHandleInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //判断controller方法返回的是否是页面
        request.setAttribute("isView",isMethodReturnView(handlerMethod));
        return true;
    }

    /**
     * 判断指定处理器方法返回的是否是页面
     * @param handlerMethod 处理器方法对象
     * @return 此处理器方法返回的是否是页面
     */
    private boolean isMethodReturnView(HandlerMethod handlerMethod){
        Method method = handlerMethod.getMethod();
        //判断处理器方法返回值是否为Void类型
        return !Void.class.equals(method.getReturnType()) &&
        //判断处理器方法是否被@ResponseBody注解修饰
        !method.isAnnotationPresent(ResponseBody.class) &&
        //判断处理器方法所在的类是否被@RestController修饰
        !handlerMethod.getBeanType().isAnnotationPresent(RestController.class) &&
        //判断处理器方法所在的类是否被@ResponseBody修饰
        !handlerMethod.getBeanType().isAnnotationPresent(ResponseBody.class);
    }
}
