package com.example.batch.repository;

import com.example.batch.shelter.Shelter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ShelterRepository extends JpaRepository<Shelter,Long> {
    public List<Shelter> findByRegionCode(String regionCode);
    public boolean existsByShelterId(String shelterId);
}
