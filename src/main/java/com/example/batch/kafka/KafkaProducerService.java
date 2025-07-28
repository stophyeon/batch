package com.example.batch.kafka;

import com.example.batch.summer.SummerShelter;
import com.example.batch.summer.SummerShelterDto;
import com.example.batch.weather.Weather;
import com.example.batch.weather.WeatherDto;
import com.example.batch.winter.WinterShelter;
import com.example.batch.winter.WinterShelterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, List<SummerShelterDto>> summerShelterTemplate;
    private final KafkaTemplate<String, List<WinterShelterDto>> winterShelterTemplate;
    private final KafkaTemplate<String, List<WeatherDto>> weatherTemplate;

    public void sendSummerShelterData(List<SummerShelterDto> data) {
        summerShelterTemplate.send("summer-shelter-data", data);
    }

    public void sendWinterShelterData(List<WinterShelterDto> data) {
        winterShelterTemplate.send("winter-shelter-data", data);
    }

    public void sendWeatherData(List<WeatherDto> data) {
        weatherTemplate.send("weather-data", data);
    }
}
