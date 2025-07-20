package com.example.batch.weather;

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

    @Override
    public void write(Chunk<? extends WeatherDto> chunk) throws Exception {
        List<WeatherDto> dtoList = new ArrayList<>(chunk.getItems());


        List<Weather> entityList = dtoList.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());

        repository.saveAll(entityList);
    }
}
