package com.example.batch.winter;

import com.example.batch.winter.repository.WinterShelterRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class WinterShelterProcessor implements ItemProcessor<WinterShelterDto, WinterShelterDto> {
    private final WinterShelterRepository repository;


    private Set<Long> existingIds;

    @PostConstruct
    public void init() {
        existingIds = repository.findAllFacilityIds(); // SELECT facility_id FROM shelter
    }

    @Override
    public WinterShelterDto process(WinterShelterDto dto) throws Exception {
        return existingIds.contains(dto.getFacilityId()) ? null : dto;
    }
}
