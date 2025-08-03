package com.example.batch.weather;


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
@RequiredArgsConstructor
public class WeatherBatchJobConfig {

    private final WeatherApiReader reader;
    private final WeatherProcessor processor;
    private final WeatherWriter writer;
    private final WeatherDeleteTasklet weatherDeleteTasklet;


    @Bean
    public Job weatherJob(JobRepository jobRepository, PlatformTransactionManager txManager) {
        log.error("Get Weather Data Scheduler run");
        return new JobBuilder("weatherJob", jobRepository)
                .start(weatherStep(jobRepository, txManager))
                .build();
    }

    @Bean
    public Step weatherStep(JobRepository jobRepository, PlatformTransactionManager txManager) {
        return new StepBuilder("weatherStep", jobRepository)
                .<WeatherDto, WeatherDto>chunk(100, txManager)
                .reader(reader)
                //.processor(processor) 중복 가능
                .writer(writer)
                .build();
    }

    @Bean
    public Job weatherDeleteJob(JobRepository jobRepository, PlatformTransactionManager txManager) {
        return new JobBuilder("weatherDeleteJob", jobRepository)
                .start(weatherDeleteStep(jobRepository, txManager))
                .build();
    }

    @Bean
    public Step weatherDeleteStep(JobRepository jobRepository, PlatformTransactionManager txManager) {
        return new StepBuilder("weatherDeleteStep", jobRepository)
                .tasklet(weatherDeleteTasklet, txManager)
                .build();
    }
}
