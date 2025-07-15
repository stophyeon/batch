package com.example.batch.winter;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class WinterShelterBatchJobConfig {

    private final WinterShelterApiReader reader;
    private final WinterShelterProcessor processor;
    private final WinterShelterWriter writer;


    @Bean
    public Job winterShelterJob(JobRepository jobRepository, PlatformTransactionManager txManager) {
        return new JobBuilder("winterShelterJob", jobRepository)
                .start(winterShelterStep(jobRepository, txManager))
                .build();
    }

    @Bean
    public Step winterShelterStep(JobRepository jobRepository, PlatformTransactionManager txManager) {
        return new StepBuilder("winterShelterStep", jobRepository)
                .<WinterShelterDto, WinterShelterDto>chunk(100, txManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
