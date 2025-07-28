package com.example.batch.summer;

import com.example.batch.kafka.KafkaProducerService;
import com.example.batch.summer.repository.SummerShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SummerShelterWriter implements ItemWriter<SummerShelterDto> {
    private final SummerShelterRepository repository;
    private final SummerShelterMapper mapper;
    private final KafkaProducerService producer;

    @Override
    public void write(Chunk<? extends SummerShelterDto> chunk) throws Exception {
        List<SummerShelterDto> dtoList = new ArrayList<>(chunk.getItems());
        //DB 저장 로직
        List<SummerShelter> entityList = dtoList.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());

        repository.saveAll(entityList);
        //KAFKA 데이터 전파
        producer.sendSummerShelterData(entityList);
    }
}
