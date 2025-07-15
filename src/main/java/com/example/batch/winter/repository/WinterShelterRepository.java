package com.example.batch.winter.repository;

import com.example.batch.winter.WinterShelter;
import com.example.batch.winter.WinterShelterDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface WinterShelterRepository extends JpaRepository<WinterShelter,Long> {
    public List<WinterShelter> findByRegionCode(String regionCode);
    public boolean existsByShelterId(String shelterId);
    public void save(WinterShelterDto dto);
}
