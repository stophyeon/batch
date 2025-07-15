package com.example.batch.winter;

import com.example.batch.winter.repository.WinterShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WinterShelterProcessor implements ItemProcessor<WinterShelterDto, WinterShelterDto> {
    private final WinterShelterRepository repository;


    @Override
    public WinterShelterDto process(WinterShelterDto dto) throws Exception {
        boolean exist = repository.existsByFacilityId(dto.getFacilityId());
        return exist ? null : dto;
    }
}
