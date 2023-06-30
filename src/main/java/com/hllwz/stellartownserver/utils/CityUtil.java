package com.hllwz.stellartownserver.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.hllwz.stellartownserver.entity.weatherApi.Location;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * 城市工具类
 * @author Linorman
 * @version 1.0.0
 */
@Slf4j
public class CityUtil {

    private final static String WEATHER_API_KEY = "d418896d9b2c4f8080f6683620520ec6";
    private final static String CITY_API_URL = "https://geoapi.qweather.com/v2/city/lookup?key=" + WEATHER_API_KEY + "&location=";

    /**
     * 根据经纬度获取城市代码
     * @param longitude
     * @param latitude
     * @return String
     */
    public static String getCityCodeByLocation(String longitude, String latitude) {
        String url = CITY_API_URL + longitude + "," + latitude;
        Map<String, Object> map = null;
        try {
            map = sendGetRequest(url);
        } catch (IOException e) {
            log.error("通过位置获取城市码请求失败");
        }
        if (map == null) {
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String location = "";
        try {
            location = objectMapper.writeValueAsString(map.get("location"));
        } catch (Exception e) {
            log.error("JSON转换失败");
        }
        List<Location> locationList = new ArrayList<>();

        Gson gson = new Gson();
        Location[] locations = gson.fromJson(location, Location[].class);
        if (locations == null) {
            return null;
        }
        Collections.addAll(locationList, locations);

        return locationList.get(0).getId();
    }

    /**
     * 根据城市获取城市代码
     * @param city
     * @return String
     */
    public static String getCityCodeByCity(String city) {
        String url = CITY_API_URL + city;
        Map<String, Object> map = null;
        try {
            map = sendGetRequest(url);
        } catch (IOException e) {
            log.error("通过城市名获取城市码请求失败");
        }
        if (map == null) {
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String location = "";
        try {
            location = objectMapper.writeValueAsString(map.get("location"));
        } catch (JsonProcessingException e) {
            log.error("JSON转换失败");
        }
        List<Location> locationList = new ArrayList<>();

        Gson gson = new Gson();
        Location[] locations = gson.fromJson(location, Location[].class);

        if (locations == null) {
            return null;
        }
        Collections.addAll(locationList, locations);
        return locationList.get(0).getId();
    }

    /**
     * 根据城市名获取经纬度
     * @param city
     * @return Map<String, Object>
     */
    public static Map<String, String> getLocationByCity(String city) {
        String url = CITY_API_URL + city;
        Map<String, Object> map = null;
        try {
            map = sendGetRequest(url);
        } catch (IOException e) {
            log.error("通过城市名获取经纬度请求失败");
        }
        if (map == null) {
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String lat = "";
        String lon = "";
        String location = "";
        try {
            location = objectMapper.writeValueAsString(map.get("location"));
        } catch (JsonProcessingException e) {
            log.error("JSON转换失败");
        }
        List<Location> locationList = new ArrayList<>();

        Gson gson = new Gson();
        Location[] locations = gson.fromJson(location, Location[].class);

        if (locations == null) {
            return null;
        }

        Collections.addAll(locationList, locations);
        lat = locationList.get(0).getLat();
        lon = locationList.get(0).getLon();
        Map<String, String> locationMap = Map.of("lat", lat, "lon", lon);

        return locationMap;
    }

    /**
     * 根据经纬度获取城市名
     * @param longitude
     * @param latitude
     * @return String
     */
    public static String getCityByLocation(String longitude, String latitude) {
        String url = CITY_API_URL + longitude + "," + latitude;
        Map<String, Object> map = null;
        try {
            map = sendGetRequest(url);
        } catch (IOException e) {
            log.error("通过经纬度获取城市名请求失败");
        }
        if (map == null) {
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String location = "";
        try {
            location = objectMapper.writeValueAsString(map.get("location"));
        } catch (JsonProcessingException e) {
            log.error("JSON转换失败");
        }
        List<Location> locationList = new ArrayList<>();

        Gson gson = new Gson();
        Location[] locations = gson.fromJson(location, Location[].class);
        if (locations == null) {
            return null;
        }
        Collections.addAll(locationList, locations);

        return locationList.get(0).getName();
    }


    /**
     * http get请求
     * @param url
     * @return
     * @throws IOException
     */
    public static Map<String, Object> sendGetRequest(String url) throws IOException {

        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();


        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept-Encoding", "gzip");

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            return null;
        }

        BufferedReader in;
        if ("gzip".equalsIgnoreCase(conn.getContentEncoding())) {
            in = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream()), StandardCharsets.UTF_8));
        } else {
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        }

        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            map = objectMapper.readValue(response.toString(), new TypeReference<>() {
            });

        } catch (IOException e) {
            log.error("JSON转换失败");
        }
        return map;
    }
}