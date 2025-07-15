package com.example.batch;

import com.example.batch.winter.WinterShelterDto;
import com.example.batch.winter.WinterShelterMapper;
import com.example.batch.winter.WinterShelterVo;
import com.example.batch.winter.repository.WinterShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shelter")
public class TestController {
    private final WinterShelterRepository repository;
    private final WinterShelterMapper mapper;

    @Value("${safe.api.winter.serviceKey}")
    private String serviceKey;
    @Value("${safe.api.winter.url}")
    private String url;

    @PostMapping("/test")
    public String test(){

        StringBuilder urlBuilder = new StringBuilder(url);
        urlBuilder.append("?" + "serviceKey=").append(serviceKey);
        urlBuilder.append("&" + "returnType=json");


        RestTemplate rest = new RestTemplate();
        ResponseEntity<WinterShelterVo> response = rest.getForEntity(urlBuilder.toString(), WinterShelterVo.class);
        List<WinterShelterDto> shelters= response.getBody().getBody();
        repository.saveAll(mapper.toEntityList(shelters));
        return "SUCCESS";
    }
}
