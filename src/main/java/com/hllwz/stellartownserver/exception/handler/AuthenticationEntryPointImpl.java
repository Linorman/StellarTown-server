package com.hllwz.stellartownserver.exception.handler;

import com.alibaba.fastjson.JSONObject;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.common.ResultCode;
import com.hllwz.stellartownserver.utils.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 认证异常处理器
 * @author Linorman
 * @version 1.0.0
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    private ResponseResult result;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (authException instanceof UsernameNotFoundException) {
            result = ResponseResult.error(ResultCode.USER_NOT_EXIST,null);
        } else if (authException instanceof BadCredentialsException) {
            result = ResponseResult.error(ResultCode.LOGIN_ACCOUNT_OR_PASSWORD_ERROR,null);
        } else if (authException instanceof InsufficientAuthenticationException) {
            result = ResponseResult.error(ResultCode.NEED_LOGIN,null);
        } else {
            result = ResponseResult.error(ResultCode.SYSTEM_ERROR,null);
        }
        WebUtil.renderString(response, JSONObject.toJSONString(result));
    }
}
