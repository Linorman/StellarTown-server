package com.hllwz.stellartownserver.config;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hllwz.stellartownserver.entity.UserInfo;
import com.hllwz.stellartownserver.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 应用程序配置类
 * @author Linorman
 * @version 1.0.1
 */
@Configuration
public class ApplicationConfig {
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 用户详细信息服务
     * @return 用户详细信息服务
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // 从数据库中读取用户信息
            LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(UserInfo::getUsername, username);
            var user = userInfoMapper.selectOne(wrapper);
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException("用户不存在");
        };
    }

    /**
     * 身份验证提供程序
     * @return 身份校验机制、身份验证提供程序
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * 设置身份验证管理器
     * @param config
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * 提供编码机制
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

