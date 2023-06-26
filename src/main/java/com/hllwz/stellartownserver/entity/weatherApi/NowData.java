package com.hllwz.stellartownserver.entity.weatherApi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 天气API信息实体类-NowData
 *
 * @author Linorman
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NowData {

    private String obsTime;
    private String temp;
    private String feelsLike;
    private String icon;
    private String text;
    private String wind360;
    private String windDir;
    private String windScale;
    private String windSpeed;
    private String humidity;
    private String precip;
    private String pressure;
    private String vis;
    private String cloud;
    private String dew;
}