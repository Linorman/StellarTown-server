package com.hllwz.stellartownserver.service;

import com.hllwz.stellartownserver.common.ResponseResult;

/**
 * 天文Service接口
 * @author Linorman
 * @version 1.0.0
 */
public interface AstronomyService {

    /**
     * 根据城市名获取天文信息
     * @param cityName
     * @return responseResult
     */
    public ResponseResult getAstronomyByCityName(String cityName);

    /**
     * 根据经纬度获取天文信息
     * @param lon
     * @param lat
     * @return responseResult
     */
    public ResponseResult getAstronomyByLonLat(String lon, String lat);

    /**
     * 根据城市名获取蓝调、黄金时间
     * @param cityName
     * @return responseResult
     */
    public ResponseResult getBlueAndGoldenTimeByCityName(String cityName);

    /**
     * 根据经纬度获取蓝调、黄金时间
     * @param lon
     * @param lat
     * @return responseResult
     */
    public ResponseResult getBlueAndGoldenTimeByLonLat(String lon, String lat);

    /**
     * 获取曙暮光时间
     * @param lon
     * @param lat
     * @return responseResult
     */
    public ResponseResult getTwilightTime(String lon, String lat);
}
