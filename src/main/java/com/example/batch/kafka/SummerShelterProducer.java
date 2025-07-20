package com.example.batch.kafka;

import com.example.batch.summer.SummerShelter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SummerShelterProducer {
    private final KafkaTemplate<String,List<SummerShelter>> kafkaTemplate;

    public void sendSummerShelterData(List<SummerShelter> shelterList) {
        kafkaTemplate.send("summer-shelter-data", shelterList);
    }
}
