package com.hllwz.stellartownserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hllwz.stellartownserver.entity.RedisCache;
import com.hllwz.stellartownserver.common.Role;
import com.hllwz.stellartownserver.dto.RegisterRequest;
import com.hllwz.stellartownserver.entity.UserInfo;
import com.hllwz.stellartownserver.dto.AuthenticationRequest;
import com.hllwz.stellartownserver.vo.AuthenticationResponse;
import com.hllwz.stellartownserver.mapper.UserInfoMapper;
import com.hllwz.stellartownserver.service.AuthenticationService;
import com.hllwz.stellartownserver.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 授权测试服务实现类
 * @author Linorman
 * @version 1.0.0
 */
@Service
@Deprecated
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserInfoMapper repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RedisCache redisCache;
    private final AuthenticationManager authenticationManager;
    @Override
    @Deprecated
    public AuthenticationResponse register(RegisterRequest request) {
        UserInfo user = new UserInfo();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        repository.insert(user);
        String jwtToken = jwtUtil.generateToken(user);
        // 将token存储
        redisCache.setCacheObject("token:" ,jwtToken);
        // 将token返回响应
        return AuthenticationResponse
                .builder()
                .accessToken(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserInfo::getUsername, request.getUsername());
        UserInfo user = repository.selectOne(wrapper);
        var jwtToken = jwtUtil.generateToken(user);
        // 将token存储
        redisCache.setCacheObject("token:" ,jwtToken);
        // 将token返回响应
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

}

