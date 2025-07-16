package com.example.batch;

import com.example.batch.summer.SummerShelterDto;
import com.example.batch.summer.SummerShelterMapper;
import com.example.batch.summer.SummerShelterVo;
import com.example.batch.summer.repository.SummerShelterRepository;
import com.example.batch.winter.WinterShelterDto;
import com.example.batch.winter.WinterShelterMapper;
import com.example.batch.winter.WinterShelterVo;
import com.example.batch.winter.repository.WinterShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shelter")
public class TestController {
    private final WinterShelterRepository repository1;
    private final WinterShelterMapper mapper1;

    private final SummerShelterRepository repository2;
    private final SummerShelterMapper mapper2;

    @Value("${safe.api.winter.serviceKey}")
    private String serviceKey1;
    @Value("${safe.api.winter.url}")
    private String url1;
    @Value("${safe.api.summer.serviceKey}")
    private String serviceKey2;
    @Value("${safe.api.summer.url}")
    private String url2;
    @PostMapping("/test")
    public String test(){

        StringBuilder urlBuilder = new StringBuilder(url1);
        urlBuilder.append("?" + "serviceKey=").append(serviceKey1);
        urlBuilder.append("&" + "returnType=json");


        RestTemplate rest = new RestTemplate();
        ResponseEntity<WinterShelterVo> response = rest.getForEntity(urlBuilder.toString(), WinterShelterVo.class);
        List<WinterShelterDto> shelters= response.getBody().getBody();
        repository1.saveAll(mapper1.toEntityList(shelters));
        return "Winter SUCCESS";
    }
    @PostMapping("/test2")
    public String test2(){

        StringBuilder urlBuilder = new StringBuilder(url2);
        urlBuilder.append("?" + "serviceKey=").append(serviceKey2);
        urlBuilder.append("&" + "returnType=json");


        RestTemplate rest = new RestTemplate();
        ResponseEntity<SummerShelterVo> response = rest.getForEntity(urlBuilder.toString(), SummerShelterVo.class);
        List<SummerShelterDto> shelters= response.getBody().getBody();
        repository2.saveAll(mapper2.toEntityList(shelters));
        return "Summer SUCCESS";
    }
}
