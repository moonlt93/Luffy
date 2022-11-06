package com.zerobase.luffy.configuration;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@EnableBatchProcessing // 배치에 필요한 설정들을 자동으로 주입해줌
@Configuration
public class TestJobConfiguration{

    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils() { // (2)
        return new JobLauncherTestUtils();
    }

}
