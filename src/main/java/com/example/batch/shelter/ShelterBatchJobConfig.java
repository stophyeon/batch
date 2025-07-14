package com.example.batch.shelter;

import com.example.batch.shelter.ShelterApiReader;
import com.example.batch.shelter.ShelterProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ShelterBatchJobConfig {

    private final ShelterApiReader reader;
    private final ShelterProcessor processor;


    @Bean
    public Job shelterJob(JobRepository jobRepository, PlatformTransactionManager txManager) {
        return new JobBuilder("shelterJob", jobRepository)
                .start(shelterStep(jobRepository, txManager))
                .build();
    }

    @Bean
    public Step shelterStep(JobRepository jobRepository, PlatformTransactionManager txManager) {
        return new StepBuilder("shelterStep", jobRepository)
                .<ShelterDto, ShelterDto>chunk(100, txManager)
                .reader(reader)
                .processor(processor)
                //.writer(writer)
                .build();
    }
}
