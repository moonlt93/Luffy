package com.zerobase.luffy.batch;


import com.zerobase.luffy.member.admin.entity.Photoes;
import com.zerobase.luffy.member.admin.entity.ProductDetail;
import com.zerobase.luffy.member.admin.service.Impl.ProductServiceImpl;
import com.zerobase.luffy.member.bm.entity.ManagerProduct;
import com.zerobase.luffy.member.bm.entity.Photo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.jsr.item.ItemProcessorAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;


@Slf4j
@RequiredArgsConstructor
@Configuration
public class ProductJob {


    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final EntityManagerFactory entityManagerFactory;


    private int chunkSize = 10;

    @Bean
    public Job ProductJob_batchBuild() {
        return jobBuilderFactory.get("ProductJob")
                .start(PhotoesJob_step())
                .next(ProductJob_step())
                .build();
    }

    @Bean
    public Step ProductJob_step() {
        return stepBuilderFactory.get("ProductJob_step")
                .<ManagerProduct, ProductDetail>chunk(chunkSize)
                .reader(ProductJob_dbItemReader())
                .processor(ProductJob_processor())
                .writer(ProductJob_ItemWriter())
                .build();
    }


    private ItemProcessor<ManagerProduct, ProductDetail> ProductJob_processor() {

        return manageProduct -> {
            return new ProductDetail(manageProduct.getId(), manageProduct.getProductName(), manageProduct.getProductStatus(),
                        manageProduct.getPnt(), manageProduct.getPrice(), manageProduct.getFileName(), manageProduct.getUrlFileName(), manageProduct.getWriter()
                      , manageProduct.getRegDt(), manageProduct.getCategoryName(), manageProduct.getContent(),manageProduct.getCompanyName(),manageProduct.getPhotoList());
        };
    }

    /* 관리자가 등록한 상품만 업데이트 */
    @Bean
    public JpaCursorItemReader<ManagerProduct> ProductJob_dbItemReader() {

        return new JpaCursorItemReaderBuilder<ManagerProduct>()
                .name("ProductJob_JpaPagingItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT mp FROM ManagerProduct mp where mp.productStatus='Processing' ORDER BY mp.id asc")
                .build();


    }

    @Bean
    public JpaItemWriter<ProductDetail> ProductJob_ItemWriter() {

        JpaItemWriter<ProductDetail> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);

        return jpaItemWriter;

    }

    @Bean
    public Step PhotoesJob_step() {
        return stepBuilderFactory.get("PhotoesJob_step")
                .<Photo, Photoes>chunk(chunkSize)
                .reader(PhotoesJob_dbItemReader())
                .processor(PhotoesJob_processor())
                .writer(PhotoesJob_ItemWriter())
                .build();
    }
    @Bean
    public JpaPagingItemReader<Photo> PhotoesJob_dbItemReader(
    ) {

        return new JpaPagingItemReaderBuilder<Photo>()
                .name("ProductJob_JpaPagingItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT ph FROM Photo as ph ORDER BY ph.id asc")
                .build();

    }

    private ItemProcessor<Photo, Photoes> PhotoesJob_processor() {


        return photo -> {
            log.info("id가 잘넘어가는지"+photo.getManagerProduct().getId());
            long YourId= photo.getManagerProduct().getId();

            return new Photoes(photo.getId(),photo.getWriter()
                    ,photo.getFileName(), photo.getUrlFileName()
                    ,photo.getProductName(),YourId
            );
        };
    }


    @Bean
    public JpaItemWriter<Photoes> PhotoesJob_ItemWriter() {

        JpaItemWriter<Photoes> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);

        return jpaItemWriter;

    }





}
