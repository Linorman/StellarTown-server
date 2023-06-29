package com.hllwz.stellartownserver.entity.astronomyApi;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * 月相信息实体类
 * @auther Linorman
 * @version 1.0.0
 */
@Data
@RequiredArgsConstructor
public class MoonPhase {

    private String fxTime;
    private String value;
    private String name;
    private String illumination;
    private String icon;
}