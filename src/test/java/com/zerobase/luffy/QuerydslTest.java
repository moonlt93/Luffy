package com.zerobase.luffy;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zerobase.luffy.configuration.QuerydslConfig;
import com.zerobase.luffy.member.user.entity.Member;
import com.zerobase.luffy.member.user.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
@Import(QuerydslConfig.class)
public class QuerydslTest {

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Autowired
    MemberRepository memberRepository;


    @Autowired
    EntityManager em;


    @BeforeEach
    void init(){

        Member member1= Member.builder()
                .id(1L)
                .name("진수")
                .password(String.valueOf(123))
                .username("kal")
                .build();


        memberRepository.save(member1);

    }




}
