package com.zerobase.luffy.batch;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class testJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;



    @Bean
    public Job exampleJob() throws Exception{
        return  jobBuilderFactory.get("exampleJob")
                .start(exampleStep()).build();
    }

    @Bean
    @JobScope
    public Step exampleStep() throws Exception{
        return stepBuilderFactory.get("exampleStep")
                .<Market,Market>chunk(10)
                .reader(ExampleReader())
                .processor(exampleProcessor())
                .writer(exampleWriter())
                .build();
    }

    @Bean
    @JobScope
    public JpaPagingItemReader<Market> ExampleReader() throws Exception{
    log.info("=> reader value:");
    Map<String,Object> paramValue= new HashMap<>();
    paramValue.put("price",1000);

    return new JpaPagingItemReaderBuilder<Market>()
            .pageSize(10)
            .parameterValues(paramValue)
            .queryString("select m from Market m Where m.price >= :price")
            .entityManagerFactory(entityManagerFactory)
            .name("ExampleReader")
            .build();

    }


    @Bean
    @StepScope
    public ItemProcessor<Market,Market> exampleProcessor(){
        return new ItemProcessor<Market, Market>() {
            @Override
            public Market process(Market item) throws Exception {

                log.info("===> processor do :"+item);

                item.setPrice(item.getPrice()+1000);
                return item;
            }
        };
    }
    @Bean
    @StepScope
    public JpaItemWriter<Market> exampleWriter(){
        log.info("==> writer value :");


        return new JpaItemWriterBuilder<Market>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }


}
