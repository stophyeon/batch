package com.example.batch.summer;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class SummerShelterApiReader implements ItemReader<SummerShelterDto> {

    private List<SummerShelterDto> shelters;

    @Value("${safe.api.summer.serviceKey}")
    private String serviceKey;

    @Value("${safe.api.summer.url}")
    private String url;
    private int index=0;
    @Override
    public SummerShelterDto read() throws Exception {
        if(shelters==null) {

            StringBuilder urlBuilder = new StringBuilder(url);
            urlBuilder.append("?" + "serviceKey=").append(serviceKey);
            urlBuilder.append("&" + "returnType=json");


            RestTemplate rest = new RestTemplate();
            ResponseEntity<SummerShelterDto[]> response = rest.getForEntity(urlBuilder.toString(), SummerShelterDto[].class);
            shelters = Arrays.asList(response.getBody());
        }

        if (index < shelters.size()) {
            return shelters.get(index++);
        } else {
            return null;
        }



    }
}