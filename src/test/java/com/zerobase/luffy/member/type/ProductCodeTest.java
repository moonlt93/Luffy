package com.zerobase.luffy.member.type;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;
@Slf4j
@SpringBootTest
class ProductCodeTest {

    @Test
    @DisplayName("enum 속도 측정 map 이용")
    void eunmSpeedCheck(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 100000000; i++) {
            ProductCode.find("판매중");
        }
        for (int i = 0; i < 100000000; i++) {
            ProductCode.find("처리중");
        }
        stopWatch.stop();
        log.info("2억건처리시 2초 이하면 합격");
        log.info("HashMap이용시 :"+stopWatch.getTotalTimeSeconds()+"s");
        //hashMap이 압도적으로 빠름

    }

}