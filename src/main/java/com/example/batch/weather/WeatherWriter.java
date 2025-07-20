package com.example.batch.weather;

import com.example.batch.kafka.WeatherProducer;
import com.example.batch.summer.SummerShelter;
import com.example.batch.summer.SummerShelterDto;
import com.example.batch.weather.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WeatherWriter implements ItemWriter<WeatherDto> {
    private final WeatherRepository repository;
    private final WeatherMapper mapper;
    private final WeatherProducer producer;

    @Override
    public void write(Chunk<? extends WeatherDto> chunk) throws Exception {
        List<WeatherDto> dtoList = new ArrayList<>(chunk.getItems());
        //DB 저장 로직
        List<Weather> entityList = dtoList.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());

        repository.saveAll(entityList);
        //KAFKA 데이터 전파 로직
        producer.sendWeatherData(entityList);
    }
}
