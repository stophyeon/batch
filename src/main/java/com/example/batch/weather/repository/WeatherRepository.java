package com.example.batch.weather.repository;

import com.example.batch.weather.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface WeatherRepository extends JpaRepository<Weather,Long> {
    //public List<WinterShelter> findByRegionCode(String regionCode);


}
