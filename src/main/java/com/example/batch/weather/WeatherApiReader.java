package com.example.batch.weather;

import com.example.batch.winter.WinterShelterDto;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class WeatherApiReader implements ItemReader<WeatherDto> {

    private List<WeatherDto> shelters;

    @Value("${safe.api.weather.serviceKey}")
    private String serviceKey;

    @Value("${safe.api.weather.url}")
    private String url;
    private int index=0;
    @Override
    public WeatherDto read() throws Exception {
        if(shelters==null) {

            StringBuilder urlBuilder = new StringBuilder(url);
            urlBuilder.append("?" + "serviceKey=").append(serviceKey);
            urlBuilder.append("&" + "returnType=json");


            RestTemplate rest = new RestTemplate();
            ResponseEntity<WeatherVo> response = rest.getForEntity(urlBuilder.toString(), WeatherVo.class);
            //shelters = Arrays.asList(response.getBody());
        }

        if (index < shelters.size()) {
            return shelters.get(index++);
        } else {
            return null;
        }



    }
}