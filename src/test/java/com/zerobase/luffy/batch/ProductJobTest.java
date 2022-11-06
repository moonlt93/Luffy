package com.zerobase.luffy.batch;


import com.zerobase.luffy.configuration.TestJobConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBatchTest
@ContextConfiguration(classes ={ TestJobConfiguration.class, testJobConfig.class})
class ProductJobTest {


    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;


    @Test
    @DisplayName("배치잡 실행")
    void Batch_test() throws Exception {


        //given
        JobParameters param = new JobParametersBuilder()
                .addString("v","0")
                .addLong("requestDate",System.currentTimeMillis())
                .toJobParameters();

    //when
        JobExecution jobExecution =jobLauncherTestUtils.launchJob(param);

    //then
    assertThat(jobExecution.getExitStatus()).isEqualTo(BatchStatus.COMPLETED);


    }

}

