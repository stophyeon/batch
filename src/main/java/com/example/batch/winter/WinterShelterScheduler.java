package com.example.batch.winter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@RequiredArgsConstructor
public class WinterShelterScheduler {
    private final JobLauncher jobLauncher;
    private final Job shelterJob;

    @Scheduled(cron = "0 */10 * * * *") // 매 10분마다
    public void runShelterJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(shelterJob, params);
    }

}
