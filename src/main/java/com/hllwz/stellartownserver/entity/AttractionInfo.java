package com.hllwz.stellartownserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


/**
 * 景点信息实体类
 *
 * @author Lyh
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttractionInfo implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String image;
    private String address;
    private String longitude;
    private String latitude;
    private String introduction;
}

