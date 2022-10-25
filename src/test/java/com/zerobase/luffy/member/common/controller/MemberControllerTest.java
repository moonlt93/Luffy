package com.zerobase.luffy.member.common.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.luffy.member.common.dto.MemberDto;
import com.zerobase.luffy.member.common.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebMvcTest(MemberControllerTest.class)
class MemberControllerTest {
    @MockBean
    private MemberService memberService;



    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


}