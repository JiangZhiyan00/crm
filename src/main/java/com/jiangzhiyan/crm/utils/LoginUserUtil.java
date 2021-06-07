package com.jiangzhiyan.crm.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 在Cookie中获取用户id工具类
 */
public class LoginUserUtil {

    /**
     * 从cookie中获取userId并解密
     * @param request
     * @return
     */
    public static Integer releaseUserIdFromCookie(HttpServletRequest request) {
        String userIdStr = CookieUtil.getCookieValue(request, "userId");
        if (StringUtils.isBlank(userIdStr)) {
            return -1;
        }
        return UserIdBase64.decoderUserId(userIdStr);
    }
}
