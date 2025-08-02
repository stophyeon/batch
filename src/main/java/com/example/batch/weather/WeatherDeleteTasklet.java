package com.example.batch.weather;

import com.example.batch.weather.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherDeleteTasklet implements Tasklet {

    private final WeatherRepository weatherRepository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        weatherRepository.deleteByBaseTimeNot("230000");
        log.error("[Batch] All weather data deleted at midnight.");
        return RepeatStatus.FINISHED;
    }
}
