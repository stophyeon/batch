package com.example.batch.weather;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WeatherConverter {
    public List<WeatherDto> convert(List<WeatherVo.Item> items){
        Map<String, List<WeatherVo.Item>> grouped = items.stream()
                .collect(Collectors.groupingBy(WeatherVo.Item::getFcstTime));

        List<WeatherDto> res = new ArrayList<>();
        for(Map.Entry<String, List<WeatherVo.Item>> entry : grouped.entrySet()){
            String key = entry.getKey();
            WeatherDto resDto = new WeatherDto();
            resDto.setFcstTime(key);
            List<WeatherVo.Item> weathers=grouped.get(key);
            for(WeatherVo.Item item :weathers){
                switch (item.getCategory()) {
                    case "PTY": resDto.setPty(item.getFcstValue()); break;
                    case "REH": resDto.setReh(item.getFcstValue()); break;
                    case "RN1": resDto.setRn1(item.getFcstValue()); break;
                    case "T1H": resDto.setT1h(item.getFcstValue()); break;
                    case "UUU": resDto.setUuu(item.getFcstValue()); break;
                    case "VEC": resDto.setVec(item.getFcstValue()); break;
                    case "VVV": resDto.setVvv(item.getFcstValue()); break;
                    case "WSD": resDto.setWsd(item.getFcstValue()); break;
                }
            }
            res.add(resDto);
        }
        return res;
    }
}
