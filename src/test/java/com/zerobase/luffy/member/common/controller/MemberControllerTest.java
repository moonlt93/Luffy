package com.zerobase.luffy.member.common.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.luffy.common.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(MemberControllerTest.class)
class MemberControllerTest {
    @MockBean
    private MemberService memberService;



    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


}