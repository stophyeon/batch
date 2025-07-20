package com.example.batch.summer.repository;

import com.example.batch.summer.SummerShelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository

public interface SummerShelterRepository extends JpaRepository<SummerShelter,Long> {
    //public List<WinterShelter> findByRegionCode(String regionCode);
    public boolean existsByFacilityId(Long shelterId);
    @Query("SELECT s.facilityId FROM SummerShelter s")
    Set<Long> findAllFacilityIds();
}
