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

    @Value("${safe.api.numOfRows}")
    private String rows;

    @Value("${safe.api.startLat}")
    private String startLat;
    @Value("${safe.api.startLot}")
    private String startLot;
    @Value("${safe.api.endLat}")
    private String endLat;
    @Value("${safe.api.endLot}")
    private String endLot;
    private int index=0;
    @Override
    public SummerShelterDto read() throws Exception {
        if(shelters==null) {

            StringBuilder urlBuilder = new StringBuilder(url);
            urlBuilder.append("?" + "serviceKey=").append(serviceKey);
            urlBuilder.append("&" + "returnType=json");
            urlBuilder.append("&" + "numOfRows=").append(rows);
            urlBuilder.append("&" + "startLat=").append(startLat);
            urlBuilder.append("&" + "startLot=").append(startLot);
            urlBuilder.append("&" + "endLat=").append(endLat);
            urlBuilder.append("&" + "endLot=").append(endLot);

            RestTemplate rest = new RestTemplate();
            ResponseEntity<SummerShelterVo> response = rest.getForEntity(urlBuilder.toString(), SummerShelterVo.class);
            shelters= response.getBody().getBody();
        }

        if (index < shelters.size()) {
            return shelters.get(index++);
        } else {
            return null;
        }



    }
}