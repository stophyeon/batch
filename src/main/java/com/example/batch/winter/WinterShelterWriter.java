package com.example.batch.winter;

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

    @Override
    public void write(Chunk<? extends WinterShelterDto> chunk) throws Exception {
        List<WinterShelterDto> dtoList = new ArrayList<>(chunk.getItems());


        List<WinterShelter> entityList = dtoList.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());

        repository.saveAll(entityList);
    }
}
