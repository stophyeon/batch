package com.example.batch.weather;

import com.example.batch.winter.WinterShelterDto;
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
public class WeatherBatchJobConfig {

    private final WeatherApiReader reader;
    private final WeatherProcessor processor;
    private final WeatherWriter writer;


    @Bean
    public Job weatherJob(JobRepository jobRepository, PlatformTransactionManager txManager) {
        return new JobBuilder("weatherJob", jobRepository)
                .start(weatherStep(jobRepository, txManager))
                .build();
    }

    @Bean
    public Step weatherStep(JobRepository jobRepository, PlatformTransactionManager txManager) {
        return new StepBuilder("weatherStep", jobRepository)
                .<WeatherDto, WeatherDto>chunk(100, txManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
