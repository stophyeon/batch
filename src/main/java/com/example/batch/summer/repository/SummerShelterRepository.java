package com.example.batch.summer.repository;

import com.example.batch.summer.SummerShelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SummerShelterRepository extends JpaRepository<SummerShelter,Long> {
    //public List<WinterShelter> findByRegionCode(String regionCode);
    public boolean existsByFacilityId(Long shelterId);

}
