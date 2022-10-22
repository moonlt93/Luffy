package com.zerobase.luffy.Impl;

import com.zerobase.luffy.member.common.entity.Member;
import com.zerobase.luffy.member.common.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceImplTest {



    @Autowired
    MemberRepository memberRepository;

    @Test
    void register(){
    //given
        Member member = new Member();
    //when
        member.setUsername("진수");
        member.setId(1L);
        member.setIp("진수");
        member.setEmail("진수");
        member.setPassword("진수");
        member.setRegDt(LocalDateTime.now());

    //then
        memberRepository.save(member);
        List<Member> list = memberRepository.findAll();

        assertNotNull(list);

    }

}