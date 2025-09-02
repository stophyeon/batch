package com.example.batch.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherScheduler {
    private final JobLauncher jobLauncher;
    private final Job weatherJob;
    private final Job weatherDeleteJob;

    @Scheduled(cron = "0 0 0/3 * * *",zone="Asia/Seoul")
    //@Scheduled(cron = "0 0/5 * * * *")
    public void runWeatherJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(weatherJob, params);
    }

    @Scheduled(cron = "0 0 2 * * *",zone="Asia/Seoul") // 매일 02시
    public void runWeatherDeleteJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis()) // 중복 방지
                .toJobParameters();

        log.error("[SCHEDULED] Running weather delete job at 00:00:00");
        jobLauncher.run(weatherDeleteJob, params);
    }
}
