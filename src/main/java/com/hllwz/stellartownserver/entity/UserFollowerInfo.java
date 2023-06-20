package com.hllwz.stellartownserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户关注信息实体类
 * @author Linorman
 * @version 1.0.0
 */
@Data
public class UserFollowerInfo implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String userId;
    private String followerId;
    private int delFlag;
}
