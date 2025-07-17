package com.example.batch;

import com.example.batch.summer.SummerShelterDto;
import com.example.batch.summer.SummerShelterMapper;
import com.example.batch.summer.SummerShelterVo;
import com.example.batch.summer.repository.SummerShelterRepository;
import com.example.batch.weather.WeatherConverter;
import com.example.batch.weather.WeatherDto;
import com.example.batch.weather.WeatherMapper;
import com.example.batch.weather.WeatherVo;
import com.example.batch.weather.repository.WeatherRepository;
import com.example.batch.winter.WinterShelterDto;
import com.example.batch.winter.WinterShelterMapper;
import com.example.batch.winter.WinterShelterVo;
import com.example.batch.winter.repository.WinterShelterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/shelter")
public class TestController {
    private final WinterShelterRepository repository1;
    private final WinterShelterMapper mapper1;

    private final SummerShelterRepository repository2;
    private final SummerShelterMapper mapper2;

    private final WeatherConverter converter;
    private final WeatherMapper mapper3;
    private final WeatherRepository repository3;
    @Value("${safe.api.winter.serviceKey}")
    private String serviceKey1;
    @Value("${safe.api.winter.url}")
    private String url1;
    @Value("${safe.api.summer.serviceKey}")
    private String serviceKey2;
    @Value("${safe.api.summer.url}")
    private String url2;
    @Value("${safe.api.weather.serviceKey}")
    private String serviceKey3;
    @Value("${safe.api.weather.url}")
    private String url3;
    private LocalDateTime now = LocalDateTime.now();

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
    @PostMapping("/test3")
    public String test3(){

        StringBuilder urlBuilder = new StringBuilder(url3);
        urlBuilder.append("?" + "serviceKey=").append(serviceKey3);
        urlBuilder.append("&" + "dataType=json");
        urlBuilder.append("&"+"base_date=").append(now.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        urlBuilder.append("&"+"base_time=").append(converter.getBaseTime());
        urlBuilder.append("&"+"nx=").append("60");
        urlBuilder.append("&"+"ny=").append("127");
        String url = urlBuilder.toString();

        RestTemplate rest = new RestTemplate();
        URI uri = URI.create(url);
        ResponseEntity<WeatherVo> response = rest.getForEntity(uri, WeatherVo.class);
        List<WeatherDto> weathers= converter.convert(response.getBody().getResponse().getBody().getItems().getItem());
        repository3.saveAll(mapper3.toEntityList(weathers));
        return "Weather SUCCESS";
    }
}
