package com.hllwz.stellartownserver.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.common.ResultCode;
import com.hllwz.stellartownserver.common.Role;
import com.hllwz.stellartownserver.dto.LoginRequest;
import com.hllwz.stellartownserver.dto.RegisterRequest;
import com.hllwz.stellartownserver.entity.UserInfo;
import com.hllwz.stellartownserver.vo.AuthenticationResponse;
import com.hllwz.stellartownserver.mapper.RedisCache;
import com.hllwz.stellartownserver.mapper.UserInfoMapper;
import com.hllwz.stellartownserver.service.UserInfoService;
import com.hllwz.stellartownserver.utils.JwtUtil;
import com.hllwz.stellartownserver.utils.SecurityUtil;
import com.hllwz.stellartownserver.vo.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户信息Service实现类
 *
 * @author Linorman
 * @version 1.0.0
 */
@Service
@Slf4j
@DS("db_stellar_town_user")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Qualifier("userInfoMapper")
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Qualifier("passwordEncoder")
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("jwtUtil")
    private JwtUtil jwtUtil;

    @Autowired
    @Qualifier("redisCache")
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    @DS("db_stellar_town_user")
    public ResponseResult register(RegisterRequest registerRequest) {
        LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserInfo::getUsername, registerRequest.getUsername());
        UserInfo user = userInfoMapper.selectOne(wrapper);
        if (user != null) {
            return ResponseResult.error(ResultCode.ACCOUNT_EXIST, null);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(registerRequest.getUsername());
        userInfo.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userInfo.setRole(Role.USER);
        userInfo.setPhoneNumber(registerRequest.getPhoneNumber());
        userInfoMapper.insert(userInfo);
        return ResponseResult.success(ResultCode.REGISTER_SUCCESS, null);
    }

    @Override
    @DS("db_stellar_town_user")
    public ResponseResult login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        if (authentication.isAuthenticated()) {
            // 生成token
            var jwtToken = jwtUtil.generateToken((UserInfo) authentication.getPrincipal());
            // 将token存储到redis中
            if (redisCache.hasKey(loginRequest.getUsername())) {
                redisCache.deleteObject(loginRequest.getUsername());
            }
            redisCache.setCacheObject(loginRequest.getUsername(), jwtToken);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(jwtToken);
            LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(UserInfo::getUsername, loginRequest.getUsername());
            UserInfo userInfo = userInfoMapper.selectOne(wrapper);
            loginResponse.setUserInfo(userInfo);

            return ResponseResult.success(ResultCode.LOGIN_SUCCESS, loginResponse);
        } else {
            return ResponseResult.error(ResultCode.LOGIN_ERROR, null);
        }
    }

    @Override
    public ResponseResult refreshToken() {
        Integer userId = SecurityUtil.getUserId();
        LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserInfo::getId, userId);
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);
        // 生成token
        var jwtToken = jwtUtil.generateToken(userInfo);
        // 将token存储到redis中
        if (redisCache.hasKey(userInfo.getUsername())) {
            redisCache.deleteObject(userInfo.getUsername());
        }
        redisCache.setCacheObject(userInfo.getUsername(), jwtToken);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setAccessToken(jwtToken);
        return ResponseResult.success(ResultCode.TOKEN_REFRESH_SUCCESS, authenticationResponse);
    }

    @Override
    public ResponseResult logout() {
        Integer userId = SecurityUtil.getUserId();
        LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserInfo::getId, userId);
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);
        if (redisCache.hasKey(userInfo.getUsername())) {
            redisCache.deleteObject(userInfo.getUsername());
            SecurityContextHolder.clearContext();
            return ResponseResult.success(ResultCode.LOGOUT_SUCCESS, null);
        } else {
            SecurityContextHolder.clearContext();
            return ResponseResult.error(ResultCode.LOGOUT_ERROR, null);
        }

    }
}
