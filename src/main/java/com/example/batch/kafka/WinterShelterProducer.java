package com.example.batch.kafka;

import com.example.batch.winter.WinterShelter;
import com.example.batch.winter.WinterShelterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WinterShelterProducer {
    private final KafkaTemplate<String, List<WinterShelter>> kafkaTemplate;


    public void sendWinterShelterData(List<WinterShelter> shelterList) {
        kafkaTemplate.send("winter-shelter-data", shelterList);
    }

}
