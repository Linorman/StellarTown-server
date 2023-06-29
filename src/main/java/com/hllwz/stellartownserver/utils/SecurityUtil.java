package com.hllwz.stellartownserver.utils;

import com.hllwz.stellartownserver.entity.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Security工具类
 * @author Linorman
 * @version 1.0.0
 */
public class SecurityUtil {
    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     **/
    public static UserInfo getLoginUser()
    {
        return (UserInfo) getAuthentication().getPrincipal();
    }

    /**
     * 获取到用户id
     * @return
     */
    public static Integer getUserId() {
        return getLoginUser().getId();
    }


}
