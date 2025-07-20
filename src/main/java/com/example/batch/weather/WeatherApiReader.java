package com.example.batch.weather;

import com.example.batch.winter.WinterShelterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WeatherApiReader implements ItemReader<WeatherDto> {

    private List<WeatherDto> weathers;

    @Value("${safe.api.weather.serviceKey}")
    private String serviceKey;

    @Value("${safe.api.weather.url}")
    private String url;
    private final WeatherConverter converter;
    private int index=0;
    @Override
    public WeatherDto read() throws Exception {
        if(weathers==null) {
            LocalDateTime now = LocalDateTime.now();
            StringBuilder urlBuilder = new StringBuilder(url);
            urlBuilder.append("?" + "serviceKey=").append(serviceKey);
            urlBuilder.append("&" + "dataType=json");
            urlBuilder.append("&"+"base_date=").append(now.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            urlBuilder.append("&"+"base_time=").append(converter.getBaseTime());
            urlBuilder.append("&"+"nx=").append("60");
            urlBuilder.append("&"+"ny=").append("127");
            String url = urlBuilder.toString();

            RestTemplate rest = new RestTemplate();
            URI uri = URI.create(url);
            ResponseEntity<WeatherVo> response = rest.getForEntity(uri, WeatherVo.class);
            weathers= converter.convert(response.getBody().getResponse().getBody().getItems().getItem());

        }

        if (index < weathers.size()) {
            return weathers.get(index++);
        } else {
            return null;
        }



    }
}