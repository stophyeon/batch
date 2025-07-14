package com.example.batch.shelter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.batch.item.ItemReader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class ShelterApiReader implements ItemReader<ShelterDto> {

    private List<ShelterDto> shelters;

    @Value("${safe.api.serviceKey}")
    private String serviceKey;
    private int index=0;
    @Override
    public ShelterDto read() throws Exception {
        if(shelters==null) {
            // REST API 호출
            StringBuilder urlBuilder = new StringBuilder("https://www.safetydata.go.kr/V2/api/DSSP-IF-00026");
            urlBuilder.append("?" + "serviceKey=").append(serviceKey);
            urlBuilder.append("&" + "returnType=json");
            String url = urlBuilder.toString();
            /* API 키 */
            RestTemplate rest = new RestTemplate();
            ResponseEntity<ShelterDto[]> response = rest.getForEntity(url, ShelterDto[].class);
            shelters = Arrays.asList(response.getBody());
        }

        if (index < shelters.size()) {
            return shelters.get(index++);
        } else {
            return null;
        }



    }
}