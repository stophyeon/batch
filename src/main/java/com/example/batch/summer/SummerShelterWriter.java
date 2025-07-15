package com.example.batch.summer;

import com.example.batch.winter.repository.WinterShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SummerShelterWriter implements ItemWriter<SummerShelterDto> {
    private final WinterShelterRepository repository;

    @Override
    public void write(Chunk<? extends SummerShelterDto> chunk) throws Exception {
        //repository.saveAll();
    }
}
