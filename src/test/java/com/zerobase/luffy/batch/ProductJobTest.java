package com.zerobase.luffy.batch;


import com.zerobase.luffy.configuration.TestJobConfiguration;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.test.context.ContextConfiguration;
@SpringBatchTest
@ContextConfiguration(classes ={ TestJobConfiguration.class, testJobConfig.class})
class ProductJobTest {



}

