package com.jiangzhiyan.crm.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限验证码注解
 * @author JiangZhiyan
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OptValue {
    //不使用default "",加了此注解必须有value
    String[] value();
}
