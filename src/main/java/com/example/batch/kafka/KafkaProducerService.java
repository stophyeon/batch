package com.example.batch.kafka;

import com.example.batch.summer.SummerShelter;
import com.example.batch.weather.Weather;
import com.example.batch.winter.WinterShelter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, List<SummerShelter>> summerShelterTemplate;
    private final KafkaTemplate<String, List<WinterShelter>> winterShelterTemplate;
    private final KafkaTemplate<String, List<Weather>> weatherTemplate;

    public void sendSummerShelterData(List<SummerShelter> data) {
        summerShelterTemplate.send("summer-shelter-data", data);
    }

    public void sendWinterShelterData(List<WinterShelter> data) {
        winterShelterTemplate.send("winter-shelter-data", data);
    }

    public void sendWeatherData(List<Weather> data) {
        weatherTemplate.send("weather-data", data);
    }
}
