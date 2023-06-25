package com.hllwz.stellartownserver.service;

import com.hllwz.stellartownserver.dto.RegisterRequest;
import com.hllwz.stellartownserver.dto.AuthenticationRequest;
import com.hllwz.stellartownserver.vo.AuthenticationResponse;

/**
 * 授权测试服务
 * @author Linorman
 */
@Deprecated
public interface AuthenticationService {
    /**
     * 注册
     * @param request
     * @return
     */
    public AuthenticationResponse register(RegisterRequest request);

    /**
     * 登录｜认证
     * @param request
     * @return
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request);

    /**
     * 保存用户token信息
     * @param user
     * @param jwtToken
     */
//    void saveUserToken(SysUser user, String jwtToken);
    /**
     * 删除用户token信息
     * @param user
     */
//    void revokeAllUserTokens(SysUser user);
}
