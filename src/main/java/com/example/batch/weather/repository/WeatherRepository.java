package com.example.batch.weather.repository;

import com.example.batch.weather.Weather;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface WeatherRepository extends JpaRepository<Weather,Long> {
    //public List<WinterShelter> findByRegionCode(String regionCode);
    @Modifying
    @Transactional
    @Query("DELETE FROM Weather w WHERE w.baseTime <> :baseTime")
    void deleteByBaseTimeNot(@Param("baseTime") String baseTime);

}
