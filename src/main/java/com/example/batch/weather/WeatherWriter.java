package com.example.batch.weather;

import com.example.batch.weather.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherWriter implements ItemWriter<WeatherDto> {
    private final WeatherRepository repository;

    @Override
    public void write(Chunk<? extends WeatherDto> chunk) throws Exception {
        //repository.saveAll();
    }
}
