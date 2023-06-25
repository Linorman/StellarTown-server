package com.hllwz.stellartownserver.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户关注信息实体类
 * @author Linorman
 * @version 1.0.0
 */
@Data
public class UserFollowerInfo implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer followerId;
    private int delFlag;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "follow_time", fill = FieldFill.INSERT)
    private Date followTime;
}
