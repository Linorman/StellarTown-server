package com.hllwz.stellartownserver.vo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * 返回景点响应类
 * @author Lyh
 * @version 1.0.0
 */
@Data
@RequiredArgsConstructor
public class ReturnAttraction implements Serializable {
    private int id;
    private String image;
    private String address;
    private String longitude;
    private String latitude;
    private String introduction;
    private Double distance;
}