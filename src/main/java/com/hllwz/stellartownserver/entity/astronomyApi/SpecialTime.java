package com.hllwz.stellartownserver.entity.astronomyApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpecialTime {
    @JsonProperty("status")
    private String status;

    @JsonProperty("msg")
    private String message;

    @JsonProperty("inputDate")
    private String inputDate;

    @JsonProperty("city")
    private String city;

    @JsonProperty("weathercode")
    private String weatherCode;

    @JsonProperty("longitude")
    private double longitude;

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("ycn")
    private String ycn;

    @JsonProperty("yzn")
    private String yzn;

    @JsonProperty("yln")
    private String yln;

    @JsonProperty("rcn")
    private String rcn;

    @JsonProperty("rzn")
    private String rzn;

    @JsonProperty("rln")
    private String rln;

    @JsonProperty("rsc")
    private String dayLength;

    @JsonProperty("ysc")
    private String nightLength;

    @JsonProperty("h1")
    private String goldenHourAMStart;

    @JsonProperty("h2")
    private String goldenHourAMEnd;

    @JsonProperty("h3")
    private String goldenHourPMStart;

    @JsonProperty("h4")
    private String goldenHourPMEnd;

    @JsonProperty("s1")
    private String blueHourAMStart;

    @JsonProperty("s2")
    private String blueHourAMEnd;

    @JsonProperty("s3")
    private String blueHourPMStart;

    @JsonProperty("s4")
    private String blueHourPMEnd;

    @JsonProperty("yc")
    private String moonRiseTime;

    @JsonProperty("yz")
    private String moonMidTime;

    @JsonProperty("yl")
    private String moonSetTime;

    @JsonProperty("tl")
    private String tl;

    @JsonProperty("th")
    private String th;

    @JsonProperty("rc")
    private String sunRiseTime;

    @JsonProperty("rl")
    private String sunSetTime;

    @JsonProperty("rz")
    private String sunMidTime;

    @JsonProperty("zc")
    private String zc;

    @JsonProperty("xq")
    private String xq;

    @JsonProperty("n")
    private String n;

    @JsonProperty("y")
    private String y;

    @JsonProperty("r")
    private String r;

    @JsonProperty("sc")
    private String sc;

    @JsonProperty("ny")
    private String ny;

    @JsonProperty("nr")
    private String nr;

    @JsonProperty("ns")
    private String ns;

    @JsonProperty("ccy")
    private String ccy;

    @JsonProperty("wh")
    private String wh;

    @JsonProperty("yx")
    private String yx;

    @JsonProperty("yt")
    private String yt;
}
