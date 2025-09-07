package com.example.batch.weather;

import com.example.batch.winter.WinterShelterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


@Component
@RequiredArgsConstructor
@Slf4j
public class WeatherApiReader implements ItemReader<WeatherDto> {



    @Value("${safe.api.weather.serviceKey}")
    private String serviceKey;

    @Value("${safe.api.weather.url}")
    private String url;

    private final WeatherConverter converter;
    private final WeatherLocationsProperties properties;
    private int i=0;

    @Override
    public WeatherDto read() throws Exception {



            if(i>=properties.getNames().size()){
                i=0;
                return null;
            }

            String name = properties.getNames().get(i);
            int nx = properties.getNx().get(i);
            int ny = properties.getNy().get(i);


            LocalDateTime now = LocalDateTime.now();
            log.error("날씨 배치 실행");
            log.error(now.toString());
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
            i++;
            if(Objects.requireNonNull(response.getBody()).getResponse()==null){
                return null;
            }
            return converter.convert(response.getBody().getResponse().getBody().getItems().getItem(), name, nx, ny, converter.getBaseTime());





    }
}