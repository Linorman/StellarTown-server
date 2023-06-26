package com.hllwz.stellartownserver.entity.weatherApi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 天气API信息实体类-DailyData
 * @author Linorman
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyData {

    private String fxDate;
    private String sunrise;
    private String sunset;
    private String moonrise;
    private String moonset;
    private String moonPhase;
    private String moonPhaseIcon;
    private String tempMax;
    private String tempMin;
    private String iconDay;
    private String textDay;
    private String iconNight;
    private String textNight;
    private String wind360Day;
    private String windDirDay;
    private String windScaleDay;
    private String windSpeedDay;
    private String wind360Night;
    private String windDirNight;
    private String windScaleNight;
    private String windSpeedNight;
    private String humidity;
    private String precip;
    private String pressure;
    private String vis;
    private String cloud;
    private String uvIndex;
}