package com.hllwz.stellartownserver.vo;

import com.hllwz.stellartownserver.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 登录响应类
 * @author Linorman
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse implements Serializable {
    private String token;
    private UserInfo userInfo;
}
