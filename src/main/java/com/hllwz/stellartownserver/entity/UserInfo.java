package com.hllwz.stellartownserver.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.hllwz.stellartownserver.common.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 用户信息实体类
 * @author Linorman
 * @version 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements UserDetails {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type= IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String phoneNumber;
    private String avatar;
    private String address;
    private String signature;
    private int gender;
    private int age;
    private int delFlag;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    private Role role;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserInfo userInfo) {
            if (this.id == null || userInfo.getId() == null) {
                return false;
            } else if (this.id.equals(userInfo.getId())) {
                return true;
            } else if (this.username.equals(userInfo.getUsername())) {
                return true;
            } else {
                return this.phoneNumber.equals(userInfo.getPhoneNumber());
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * 用户没有过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 用户没有锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 用户凭证没有过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用户是否启用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
