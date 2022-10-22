package com.zerobase.luffy.member.common.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
@Transactional
@WebAppConfiguration
class JoinControllerTest {


    @Test
    public void comment(){
        System.out.println("test");
        int num =1;

        assertEquals(1,num);

    }



}