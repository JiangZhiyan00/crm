package com.jiangzhiyan.crm.aspects;

import com.jiangzhiyan.crm.annotations.OptValue;
import com.jiangzhiyan.crm.exceptions.AuthException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 用户权限验证切面(防止无权限用户通过url方式访问资源)
 * @author JiangZhiyan
 */
@Aspect
@Component
public class PermissionAspect {

    @Resource
    private HttpSession session;

    /**
     * 权限码验证切面
     * 切入点:@annotation(com.jiangzhiyan.crm.annotations.OptValue)
     * 拦截@OptValue注解
     * @param pjp
     * @return
     */
    @Around("@annotation(com.jiangzhiyan.crm.annotations.OptValue)")
    public Object permissionAspect(ProceedingJoinPoint pjp) throws Throwable {
        //当前用户存入session中的拥有的权限码集合;
        List<String> theUserOptValues = (List<String>) session.getAttribute("optValues");
        if (theUserOptValues == null || theUserOptValues.size() == 0){
            throw new AuthException();
        }
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        OptValue optValue = method.getAnnotation(OptValue.class);
        //待访问的url需要的访问权限码数组[]
        String[] neededOptValues = optValue.value();
        for (String s:neededOptValues){
            //只要用户拥有的权限码中包含任意一个所需要的权限码则验证通过
            if (theUserOptValues.contains(s)){
                return pjp.proceed();
            }
        }
        throw new AuthException();
    }
}
