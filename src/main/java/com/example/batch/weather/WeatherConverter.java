package com.example.batch.weather;

import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WeatherConverter {
    public WeatherDto convert(List<WeatherVo.Item> items,String locName,int nx, int ny,String bseTime){
        Map<String, List<WeatherVo.Item>> grouped = items.stream()
                .collect(Collectors.groupingBy(WeatherVo.Item::getFcstTime));

        WeatherDto resDto = new WeatherDto();
        for(Map.Entry<String, List<WeatherVo.Item>> entry : grouped.entrySet()){
            String key = entry.getKey();

            resDto.setFcstTime(key);
            resDto.setLocationName(locName);
            resDto.setNx(nx);
            resDto.setNy(ny);
            resDto.setBaseTime(bseTime);
            resDto.setBaseDate(items.getFirst().getBaseDate());
            List<WeatherVo.Item> weathers=grouped.get(key);

            for(WeatherVo.Item item :weathers){
                switch (item.getCategory()) {
                    case "PTY": resDto.setPty(item.getFcstValue()); break;
                    case "PCP": resDto.setPcp(item.getFcstValue()); break;
                    case "POP": resDto.setPop(item.getFcstValue()); break;
                    case "WAV": resDto.setWav(item.getFcstValue()); break;
                    case "UUU": resDto.setUuu(item.getFcstValue()); break;
                    case "VEC": resDto.setVec(item.getFcstValue()); break;
                    case "VVV": resDto.setVvv(item.getFcstValue()); break;
                    case "WSD": resDto.setWsd(item.getFcstValue()); break;
                    case "TMP": resDto.setTmp(item.getFcstValue()); break;
                    case "SKY": resDto.setSky(item.getFcstValue()); break;
                }
            }

        }
        return resDto;
    }
    public String getBaseTime() {
        LocalTime now = LocalTime.now();
        int hour = now.getHour();

        String baseHour;

        if (hour == 0) {
            baseHour = "230000";
            return baseHour;
        } else if (hour <= 3) {
            baseHour = "020000";
            return baseHour;
        } else if (hour <= 6) {
            baseHour = "050000";
            return baseHour;
        } else if (hour <= 9) {
            baseHour = "080000";
            return baseHour;
        } else if (hour <= 12) {
            baseHour = "110000";
            return baseHour;
        } else if (hour <= 15) {
            baseHour = "140000";
            return baseHour;
        } else if (hour <= 18) {
            baseHour = "170000";
            return baseHour;
        } else if (hour <= 21) {
            baseHour = "200000";
            return baseHour;
        } else {
            baseHour = "230000";
        }

        // HHmm 형식 문자열로 반환
        return baseHour;
    }

}
