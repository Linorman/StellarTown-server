package com.hllwz.stellartownserver.service;

import com.hllwz.stellartownserver.common.ResponseResult;

/**
 * 天气Service接口
 * @author Linorman
 * @version 1.0.0
 */
public interface WeatherService {

    /**
     * 根据城市名获取实时天气信息
     * @param cityName
     * @return ResponseResult
     */
    public ResponseResult getWeatherByCityName(String cityName);

    /**
     * 根据经纬度获取实时天气信息
     * @param lon
     * @param lat
     * @return ResponseResult
     */
    public ResponseResult getWeatherByLonLat(String lon, String lat);

    /**
     * 根据城市名获取未来7天天气信息
     * @param cityName
     * @return ResponseResult
     */
    public ResponseResult getWeather7dByCityName(String cityName);

    /**
     * 根据经纬度获取未来7天天气信息
     * @param lon
     * @param lat
     * @return ResponseResult
     */
    public ResponseResult getWeather7dByLonLat(String lon, String lat);

    /**
     * 根据城市名获取经纬度
     * @param cityName
     * @return
     */
    ResponseResult getLocationByCityName(String cityName);
}
