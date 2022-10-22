package com.zerobase.luffy.member.common.repository;

import com.zerobase.luffy.member.common.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class MemberRepositoryTest {

    @Test
    void create(){
    //given
    Member member = new Member();
    assertNotNull(member);
    //when
        System.out.println("member");
    //then

    }

}