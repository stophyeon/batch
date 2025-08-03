package com.example.batch.summer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SummerShelterBatchJobConfig {

    private final SummerShelterApiReader reader;
    private final SummerShelterProcessor processor;
    private final SummerShelterWriter writer;


    @Bean
    public Job summerShelterJob(JobRepository jobRepository, PlatformTransactionManager txManager) {
        log.error("Get Summer Shelter Data Scheduler run");
        return new JobBuilder("summerShelterJob", jobRepository)
                .start(summerShelterStep(jobRepository, txManager))
                .build();
    }

    @Bean
    public Step summerShelterStep(JobRepository jobRepository, PlatformTransactionManager txManager) {
        return new StepBuilder("summerShelterStep", jobRepository)
                .<SummerShelterDto, SummerShelterDto>chunk(100, txManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
