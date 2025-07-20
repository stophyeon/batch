package com.example.batch.summer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@RequiredArgsConstructor
public class SummerShelterScheduler {
    private final JobLauncher jobLauncher;
    private final Job summerShelterJob;

    @Scheduled(cron = "0 0 1 * * *")// 매일 1번만
    public void runShelterJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(summerShelterJob, params);
    }

}
