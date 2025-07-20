package com.example.batch;

import com.example.batch.summer.SummerShelterDto;
import com.example.batch.summer.SummerShelterMapper;
import com.example.batch.summer.SummerShelterVo;
import com.example.batch.summer.repository.SummerShelterRepository;
import com.example.batch.weather.WeatherConverter;
import com.example.batch.weather.WeatherDto;
import com.example.batch.weather.WeatherMapper;
import com.example.batch.weather.WeatherVo;
import com.example.batch.weather.repository.WeatherRepository;
import com.example.batch.winter.WinterShelterDto;
import com.example.batch.winter.WinterShelterMapper;
import com.example.batch.winter.WinterShelterVo;
import com.example.batch.winter.repository.WinterShelterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/shelter")
public class TestController {
    private final Job winterShelterJob;
    private final Job summerShelterJob;
    private final Job weatherJob;
    private final JobLauncher jobLauncher;


    @PostMapping("/test1")
    public String test() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(winterShelterJob, params);
        return "Winter SUCCESS";
    }
    @PostMapping("/test2")
    public String test2() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(summerShelterJob, params);
        return "Summer SUCCESS";
    }
    @PostMapping("/test3")
    public String test3() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(weatherJob, params);
        return "Weather SUCCESS";
    }
}
