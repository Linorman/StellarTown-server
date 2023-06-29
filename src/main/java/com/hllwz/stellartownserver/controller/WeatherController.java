package com.hllwz.stellartownserver.controller;

import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 天气Controller
 * @author Linorman
 * @version 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/weather")
public class WeatherController {

    private final WeatherService weatherService;

    /**
     * 根据城市名获取实时天气信息
     * @param cityName
     * @return ResponseResult
     */
    @GetMapping("/now/cityName")
    public ResponseResult getWeatherByCityName(String cityName) {
        return weatherService.getWeatherByCityName(cityName);
    }

    /**
     * 根据经纬度获得实时天气信息
     * @param longitude
     * @param latitude
     * @return ResponseResult
     */
    @GetMapping("/now/location")
    public ResponseResult getWeatherByLocation(String longitude, String latitude) {
        return weatherService.getWeatherByLonLat(longitude, latitude);
    }

    /**
     * 根据城市名获取未来7天天气信息
     * @param cityName
     * @return ResponseResult
     */
    @GetMapping("/7d/cityName")
    public ResponseResult getWeather7dByCityName(String cityName) {
        return weatherService.getWeather7dByCityName(cityName);
    }

    /**
     * 根据经纬度获取未来7天天气信息
     * @param longitude
     * @param latitude
     * @return ResponseResult
     */
    @GetMapping("/7d/location")
    public ResponseResult getWeather7dByLocation(String longitude, String latitude) {
        return weatherService.getWeather7dByLonLat(longitude, latitude);
    }
}
