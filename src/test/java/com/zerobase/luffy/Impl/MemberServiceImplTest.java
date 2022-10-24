package com.zerobase.luffy.Impl;

import com.zerobase.luffy.member.common.dto.MemberDto;
import com.zerobase.luffy.member.common.entity.Member;
import com.zerobase.luffy.member.common.repository.MemberRepository;
import com.zerobase.luffy.member.common.service.Impl.MemberServiceImpl;
import com.zerobase.luffy.member.common.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.zerobase.luffy.member.common.dto.MemberDto.EntityBuild;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;



@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberServiceImpl;


    @Test
    void register(){

        Member member2 = Member.builder()
                .username("user22").build();
        member2.setId(22L);
        given(memberRepository.findByUsername(member2.getUsername()))
                .willReturn(Optional.of(member2));

        Member member = new Member();
        //when
        member.setUsername("user22");
        member.setId(22L);
        member.setIp("진수");
        member.setEmail("진수");
        member.setPassword("진수");
        member.setRegDt(LocalDateTime.now());


        //when
       MemberDto dto = EntityBuild(member) ;

        //given
        Boolean result = memberServiceImpl.register(dto);

        //then

        assertEquals(22L,dto.getId());
        assertEquals("user22",dto.getUserName());


    }

}