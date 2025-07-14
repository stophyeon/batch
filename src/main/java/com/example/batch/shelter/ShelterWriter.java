package com.example.batch.shelter;

import com.example.batch.repository.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShelterWriter implements ItemWriter<ShelterDto> {
    private final ShelterRepository repository;

    @Override
    public void write(Chunk<? extends ShelterDto> chunk) throws Exception {
        //repository.saveAll();
    }
}
