package com.example.batch.winter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.batch.item.ItemReader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class WinterShelterApiReader implements ItemReader<WinterShelterDto> {

    private List<WinterShelterDto> shelters;

    @Value("${safe.api.winter.serviceKey}")
    private String serviceKey;

    @Value("${safe.api.winter.url}")
    private String url;
    private int index=0;
    @Override
    public WinterShelterDto read() throws Exception {
        if(shelters==null) {

            StringBuilder urlBuilder = new StringBuilder(url);
            urlBuilder.append("?" + "serviceKey=").append(serviceKey);
            urlBuilder.append("&" + "returnType=json");


            RestTemplate rest = new RestTemplate();
            ResponseEntity<WinterShelterDto[]> response = rest.getForEntity(urlBuilder.toString(), WinterShelterDto[].class);
            shelters = Arrays.asList(response.getBody());
        }

        if (index < shelters.size()) {
            return shelters.get(index++);
        } else {
            return null;
        }



    }
}