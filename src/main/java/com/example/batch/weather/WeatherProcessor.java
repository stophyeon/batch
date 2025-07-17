package com.example.batch.weather;

import com.example.batch.weather.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherProcessor implements ItemProcessor<WeatherDto, WeatherDto> {
    private final WeatherRepository repository;


    @Override
    public WeatherDto process(WeatherDto dto) throws Exception {
        //boolean exist = repository.existsByFacilityId(dto.getFacilityId());
        //return exist ? null : dto;
        return new WeatherDto();
    }
}
