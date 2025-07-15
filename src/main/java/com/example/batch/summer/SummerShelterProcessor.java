package com.example.batch.summer;

import com.example.batch.winter.repository.WinterShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SummerShelterProcessor implements ItemProcessor<SummerShelterDto, SummerShelterDto> {
    private final WinterShelterRepository repository;


    @Override
    public SummerShelterDto process(SummerShelterDto dto) throws Exception {
        boolean exist = repository.existsByFacilityId(dto.getFacilityId());
        return exist ? null : dto;
    }
}
