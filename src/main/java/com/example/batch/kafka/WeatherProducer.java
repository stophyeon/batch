package com.example.batch.kafka;


import com.example.batch.weather.Weather;
import com.example.batch.winter.WinterShelter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherProducer {
    private final KafkaTemplate<String, List<Weather>> kafkaTemplate;

    public void sendWeatherData(List<Weather> shelterList) {
        kafkaTemplate.send("winter-data", shelterList);
    }

}
