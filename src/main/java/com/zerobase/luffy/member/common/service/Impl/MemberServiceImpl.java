package com.zerobase.luffy.member.common.service.Impl;

import com.zerobase.luffy.member.common.entity.Member;
import com.zerobase.luffy.member.common.service.MemberService;
import com.zerobase.luffy.member.common.dto.MemberDto;
import com.zerobase.luffy.member.common.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    @Override
    public boolean register(MemberDto dto) {

        Optional<Member> optionalUsername = memberRepository.findByUsername(dto.getUserName());

        if(optionalUsername.isPresent()){
            return false;
        }

        String encPassword = BCrypt.hashpw(dto.getPassword(),BCrypt.gensalt());


        Member member = Member.builder()
                .username(dto.getUserName())
                .password(encPassword)
                .phone(dto.getPhone())
                .registration(dto.getRegistration())
                .ip(dto.getIp())
                .regDt(LocalDateTime.now())
                .ROLE("ROLE_USER")
                .email(dto.getEmail())
                .build();


        memberRepository.save(member);


        return true;
    }
}
