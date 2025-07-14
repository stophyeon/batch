package com.example.batch.shelter;

import com.example.batch.repository.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ShelterProcessor implements ItemProcessor<ShelterDto, ShelterDto> {
    private final ShelterRepository repository;


    @Override
    public ShelterDto process(ShelterDto dto) throws Exception {
        boolean exist = repository.existsByShelterId(dto.getShelterId());
        return exist ? null : dto;
    }
}
