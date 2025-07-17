package com.example.batch.weather;

import lombok.Data;

@Data
public class WeatherDto {
    private String fcstTime;

    private String pty;   // 강수형태
    private String reh;   // 습도
    private String rn1;   // 1시간 강수량
    private String t1h;   // 기온
    private String uuu;   // 동서바람성분
    private String vec;   // 풍향
    private String vvv;   // 남북바람성분
    private String wsd;   // 풍속
}
