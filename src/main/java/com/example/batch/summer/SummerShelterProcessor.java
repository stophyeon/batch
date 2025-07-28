package com.example.batch.summer;

import com.example.batch.summer.repository.SummerShelterRepository;
import com.example.batch.winter.WinterShelterDto;
import com.example.batch.winter.repository.WinterShelterRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class SummerShelterProcessor implements ItemProcessor<SummerShelterDto, SummerShelterDto> {
    private final SummerShelterRepository repository;


    private Set<Long> existingIds;

    @PostConstruct
    public void init() {
        existingIds = repository.findAllFacilityIds(); // SELECT facility_id FROM shelter
    }

    @Override
    public SummerShelterDto process(SummerShelterDto dto) throws Exception {
        init();
        return existingIds.contains(dto.getFacilityId()) ? null : dto;
    }
}
