package com.zerobase.luffy.member.type;

import org.aspectj.apache.bcel.generic.FieldOrMethod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberCodeTest {


    @Test
    @DisplayName("enum스피드테스트:Stream")
    void enumStreamSpeedTest() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 100000000; i++) {
            MemberCode.find("사용중");
        }
        for (int i = 0; i < 100000000; i++) {
            MemberCode.find("등록이안된사용자");
        }
        stopWatch.stop();
        System.out.println("2억건처리시 2초 이하면 합격");
        System.out.println("Stream.Of 이용 :" +
                stopWatch.getTotalTimeSeconds() + "s");

    }

}