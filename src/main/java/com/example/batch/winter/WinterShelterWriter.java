package com.example.batch.winter;

import com.example.batch.winter.repository.WinterShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WinterShelterWriter implements ItemWriter<WinterShelterDto> {
    private final WinterShelterRepository repository;

    @Override
    public void write(Chunk<? extends WinterShelterDto> chunk) throws Exception {
        //repository.saveAll();
    }
}
