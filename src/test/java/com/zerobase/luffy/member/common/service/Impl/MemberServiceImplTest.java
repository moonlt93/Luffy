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


    @Test
    @DisplayName("회원가입테스트")
    void register() {
        //given
        Member member = Member.builder()
                .name("do").build();
        member.setId(3L);

        given(memberRepository.save(any()))
                .willReturn(Member.builder()
                        .username(member.getUsername())
                        .id(member.getId()).build());

        ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);
        //when
        MemberDto memberDto = MemberDto.builder()
                .userName("진수")
                .id(13L)
                .build();

        Boolean result = memberServiceImpl.register(memberDto);

        //then
        verify(memberRepository,times(1)).save(captor.capture());
        assertTrue(result);

    }

    @Test
    @DisplayName("회원유무 테스트")
    void memberDetails() {

        //given
        Member member = Member.builder()
                .username("js").build();
        member.setId(2L);
        given(memberRepository.findById(anyLong()))
                .willReturn(Optional.of(member));

        //when
        Optional<Member> optionalMember = memberRepository.findById(2L);

        //then
        assertEquals(2L,optionalMember.get().getId());

    }

    @Test
    void updateDetail() {



    }

    @Test
    @DisplayName("해당 유저가 없다면 탈퇴x")
    void withDraw() {

        given(memberRepository.findById(anyLong()))
                .willReturn(Optional.empty());


        //when
        memberRepository.deleteById(324L);

        Optional<Member> optionalMember = memberRepository.findById(324L);

        //then
        assertTrue(optionalMember.isEmpty());


    }
}