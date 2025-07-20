package com.example.batch.winter;

import com.example.batch.kafka.WinterShelterProducer;
import com.example.batch.summer.SummerShelter;
import com.example.batch.summer.SummerShelterDto;
import com.example.batch.winter.repository.WinterShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WinterShelterWriter implements ItemWriter<WinterShelterDto> {
    private final WinterShelterRepository repository;
    private final WinterShelterMapper mapper;
    private final WinterShelterProducer producer;

    @Override
    public void write(Chunk<? extends WinterShelterDto> chunk) throws Exception {
        List<WinterShelterDto> dtoList = new ArrayList<>(chunk.getItems());
        //DB 저장 로직
        List<WinterShelter> entityList = dtoList.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());

        repository.saveAll(entityList);

        //KAFKA 데이터 전파 로직
        producer.sendWinterShelterData(entityList);
    }
}
