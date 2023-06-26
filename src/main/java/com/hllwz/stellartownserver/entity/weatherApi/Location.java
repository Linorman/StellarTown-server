package com.hllwz.stellartownserver.entity.weatherApi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 天气API信息实体类-Location
 *
 * @author Linorman
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private String name;
    private String id;
    private String lat;
    private String lon;
    private String adm2;
    private String adm1;
    private String country;
    private String tz;
    private String utcOffset;
    private String isDst;
    private String type;
    private String rank;
    private String fxLink;

}
