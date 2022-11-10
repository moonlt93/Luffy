package com.zerobase.luffy.scheduler;

import com.zerobase.luffy.batch.ProductJob;
import com.zerobase.luffy.member.admin.repository.PhotoesRepository;
import com.zerobase.luffy.member.admin.repository.ProductDetailRepository;
import com.zerobase.luffy.member.bm.entity.ManagerProduct;
import com.zerobase.luffy.member.bm.repository.ManagerProRepository;
import com.zerobase.luffy.member.type.ProductCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobScheduler {


    private final JobLauncher jobLauncher;
    private final ProductJob productJob;

    private final ManagerProRepository managerProRepository;
    private final PhotoesRepository photoesRepository;
    private final ProductDetailRepository productDetail;

  //    @Scheduled(cron = "0 */2 * * * * ")

    @Scheduled(cron = "0 0 1 1/1 * ? ")
    public void ProductCreatedSchedule() {

        Map<String, JobParameter> confMap = new HashMap<>();
        confMap.put("v", new JobParameter("0"));
        confMap.put("date", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(confMap);

        try {
            JobExecution jobExecution = jobLauncher.run(productJob.ProductJob_batchBuild(), jobParameters);
            log.info("JOB Execution: " + jobExecution.getStatus());
            log.info("Job GetJobId: " + jobExecution.getJobId());
            log.info("Job getExitStatus: " + jobExecution.getExitStatus());
            log.info("Job getInstance : " + jobExecution.getJobInstance());
            log.info("Job getParameters : " + jobExecution.getJobParameters());


        } catch (JobExecutionException e) {
            e.printStackTrace();


        }

    }


    @Transactional
  //  @Scheduled(cron = "0 5 1 1/1 * ? ")

    @Scheduled(cron = "0 5 1 1/1 * ? ")
    public void ProductDeleteSchedule() throws InterruptedException {

        log.info("if schedule finished extra data delete");

        List<ManagerProduct> products = this.managerProRepository.findAll();


        if (products.size() > 0) {

                String code= String.valueOf(ProductCode.find("처리중"));

                log.info("delete code"+ code);
                log.info("delete list size"+products.size());
                this.managerProRepository.deleteByProductStatus(code);

                Thread.sleep(5000);
        }

    }


}
