package com.hllwz.stellartownserver.controller;

import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/now/{cityName}")
    public ResponseResult getWeatherByCityName(@PathVariable("cityName") String cityName) {
        return weatherService.getWeatherByCityName(cityName);
    }

    /**
     * 根据经纬度获得实时天气信息
     * @param longitude
     * @param latitude
     * @return ResponseResult
     */
    @GetMapping("/now/{longitude}/{latitude}")
    public ResponseResult getWeatherByLocation(@PathVariable("longitude") String longitude,
                                               @PathVariable("latitude") String latitude) {
        return weatherService.getWeatherByLonLat(longitude, latitude);
    }

    /**
     * 根据城市名获取未来7天天气信息
     * @param cityName
     * @return ResponseResult
     */
    @GetMapping("/7d/{cityName}")
    public ResponseResult getWeather7dByCityName(@PathVariable("cityName") String cityName) {
        return weatherService.getWeather7dByCityName(cityName);
    }

    /**
     * 根据经纬度获取未来7天天气信息
     * @param longitude
     * @param latitude
     * @return ResponseResult
     */
    @GetMapping("/7d/{longitude}/{latitude}")
    public ResponseResult getWeather7dByLocation(@PathVariable("longitude") String longitude,
                                                 @PathVariable("latitude") String latitude) {
        return weatherService.getWeather7dByLonLat(longitude, latitude);
    }
}
