package com.hllwz.stellartownserver.service.impl;

import com.hllwz.stellartownserver.common.ResponseResult;
import com.hllwz.stellartownserver.common.ResultCode;
import com.hllwz.stellartownserver.entity.weatherApi.DailyData;
import com.hllwz.stellartownserver.entity.weatherApi.NowData;
import com.hllwz.stellartownserver.service.WeatherService;
import com.hllwz.stellartownserver.utils.CityUtil;
import com.hllwz.stellartownserver.utils.WeatherUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 天气Service实现类
 * @author Linorman
 * @version 1.0.0
 */
@Service
public class WeatherServiceImpl implements WeatherService {
    @Override
    public ResponseResult getWeatherByCityName(String cityName) {
        String cityCode = CityUtil.getCityCodeByCity(cityName);
        NowData nowData = new NowData();
        nowData = WeatherUtil.getNowWeather(cityCode);
        if (nowData == null) {
            return ResponseResult.error(ResultCode.WEATHER_GET_ERROR, null);
        }
        return ResponseResult.success(ResultCode.WEATHER_GET_SUCCESS, nowData);
    }

    @Override
    public ResponseResult getWeatherByLonLat(String lon, String lat) {
        String cityCode = CityUtil.getCityCodeByLocation(lon, lat);
        NowData nowData = new NowData();
        nowData = WeatherUtil.getNowWeather(cityCode);
        if (nowData == null) {
            return ResponseResult.error(ResultCode.WEATHER_GET_ERROR, null);
        }
        return ResponseResult.success(ResultCode.WEATHER_GET_SUCCESS, nowData);
    }

    @Override
    public ResponseResult getWeather7dByCityName(String cityName) {
        String cityCode = CityUtil.getCityCodeByCity(cityName);
        List<DailyData> dailyDataList = WeatherUtil.getSevenDayWeather(cityCode);
        if (dailyDataList == null) {
            return ResponseResult.error(ResultCode.WEATHER_GET_ERROR, null);
        }
        return ResponseResult.success(ResultCode.WEATHER_GET_SUCCESS, dailyDataList);
    }

    @Override
    public ResponseResult getWeather7dByLonLat(String lon, String lat) {
        String cityCode = CityUtil.getCityCodeByLocation(lon, lat);
        List<DailyData> dailyDataList = WeatherUtil.getSevenDayWeather(cityCode);
        if (dailyDataList == null) {
            return ResponseResult.error(ResultCode.WEATHER_GET_ERROR, null);
        }
        return ResponseResult.success(ResultCode.WEATHER_GET_SUCCESS, dailyDataList);
    }
}
