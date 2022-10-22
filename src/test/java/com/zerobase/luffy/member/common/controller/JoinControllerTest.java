package com.zerobase.luffy.member.common.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@WebAppConfiguration
class JoinControllerTest {


    @Test
    public void comment(){
        System.out.println("test");
        int num =2;

        assertEquals(2,num);

    }



}