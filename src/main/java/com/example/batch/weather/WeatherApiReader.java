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
import java.util.ArrayList;
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
    private final WeatherLocationsProperties properties;
    private int index=0;
    private int locationIndex = 0;
    @Override
    public WeatherDto read() throws Exception {
        if (weathers == null || index >= weathers.size()) {
            if (locationIndex >= properties.getNames().size()) {
                return null; // 모든 지역에 대해 처리 완료
            }

            String name = properties.getNames().get(locationIndex);
            int nx = properties.getNx().get(locationIndex);
            int ny = properties.getNy().get(locationIndex);
            locationIndex++; // 다음 지역으로 이동

            LocalDateTime now = LocalDateTime.now();

            StringBuilder urlBuilder = new StringBuilder(url);
            urlBuilder.append("?serviceKey=").append(serviceKey);
            urlBuilder.append("&dataType=json");
            urlBuilder.append("&base_date=").append(now.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            urlBuilder.append("&base_time=").append(converter.getBaseTime());
            urlBuilder.append("&nx=").append(nx);
            urlBuilder.append("&ny=").append(ny);

            RestTemplate restTemplate = new RestTemplate();
            URI uri = URI.create(urlBuilder.toString());
            ResponseEntity<WeatherVo> response = restTemplate.getForEntity(uri, WeatherVo.class);

            weathers=converter.convert(response.getBody().getResponse().getBody().getItems().getItem(),name);
            index = 0;
        }

        return weathers.get(index++);
    }
}