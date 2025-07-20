package com.example.batch.summer;

import com.example.batch.summer.repository.SummerShelterRepository;
import com.example.batch.winter.WinterShelterMapper;
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
public class SummerShelterWriter implements ItemWriter<SummerShelterDto> {
    private final SummerShelterRepository repository;
    private final SummerShelterMapper mapper;

    @Override
    public void write(Chunk<? extends SummerShelterDto> chunk) throws Exception {
        List<SummerShelterDto> dtoList = new ArrayList<>(chunk.getItems());


        List<SummerShelter> entityList = dtoList.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());

        repository.saveAll(entityList);
    }
}
