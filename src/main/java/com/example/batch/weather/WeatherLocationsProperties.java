package com.example.batch.weather;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "safe.api.weather.locations")
@Getter
@Setter
public class WeatherLocationsProperties {
    private List<String> names;
    private List<Integer> nx;
    private List<Integer> ny;
}
