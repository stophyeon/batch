package com.example.batch.summer;

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
public class SummerShelterScheduler {
    private final JobLauncher jobLauncher;
    private final Job summerShelterJob;

    @Scheduled(cron = "0 0 1 * * *",zone="Asia/Seoul")// 매일 1번만
    public void runSummerShelterJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(summerShelterJob, params);
    }

}
