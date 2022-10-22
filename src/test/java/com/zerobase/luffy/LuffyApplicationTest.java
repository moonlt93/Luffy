package com.zerobase.luffy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;


@SpringBootTest
@EnableSpringConfigured
class LuffyApplicationTest {
    @Test
    void contextLoads() {
        System.out.println("test메시지");
    }

}