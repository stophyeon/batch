package com.example.batch.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@RequiredArgsConstructor
public class WeatherScheduler {
    private final JobLauncher jobLauncher;
    private final Job weatherJob;

    @Scheduled(cron = "0 0 2,5,8,11,14,17,20,23 * * *") // 매 10분마다
    public void runShelterJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(weatherJob, params);
    }
}
