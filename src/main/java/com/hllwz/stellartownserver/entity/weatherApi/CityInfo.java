package com.hllwz.stellartownserver.entity.weatherApi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 天气API信息实体类-CityInfo
 * @author Linorman
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Deprecated
public class CityInfo {
    private String code;
    private List<Location> location;
    private CityRefer refer;

    public String serialize() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CityInfo deserialize(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, CityInfo.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
