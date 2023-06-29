package com.hllwz.stellartownserver.entity.astronomyApi;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 日出日落时间数据实体类
 * @author Linorman
 * @version 1.0.0
 */
@Data
@RequiredArgsConstructor
public class TwilightData {
    private String sunrise;
    private String sunset;
    private String moonrise;
    private String moonset;
    private String civiDawnStart;
    private String civiDawnEnd;
    private String nautDawnStart;
    private String nautDawnEnd;
    private String astroDawnStart;
    private String astroDawnEnd;
}
