package com.zerobase.luffy.member.common.service.Impl;

import com.zerobase.luffy.member.common.dto.MemberDto;
import com.zerobase.luffy.member.common.entity.Member;
import com.zerobase.luffy.member.common.repository.MemberRepository;
import com.zerobase.luffy.member.common.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.NotNull;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

    @Mock
    private  MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberServiceImpl;


}