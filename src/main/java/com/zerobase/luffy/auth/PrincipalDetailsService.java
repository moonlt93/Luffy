package com.zerobase.luffy.auth;

import com.zerobase.luffy.member.bm.entity.BrandManager;
import com.zerobase.luffy.member.bm.repository.ManagerRepository;
import com.zerobase.luffy.member.user.dto.MemberDto;
import com.zerobase.luffy.member.user.entity.Member;
import com.zerobase.luffy.member.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {


    private final MemberRepository memberRepository;
    private final ManagerRepository managerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<Member> optionalMember = memberRepository.findByUsername(username);

        if(optionalMember.isEmpty()){
           BrandManager bm= managerRepository.findByUsername(username)
                   .orElseThrow(() -> new UsernameNotFoundException("잘못된 username입니다."));

            MemberDto memberDto = MemberDto.managerEntityBuild(bm);
            Member member = Member.builder()
                    .id(memberDto.getId())
                    .username(memberDto.getUserName())
                    .password(memberDto.getPassword())
                    .ROLE(memberDto.getROLE())
                    .email(memberDto.getEmail())
                    .phone(memberDto.getPhone())
                    .email(memberDto.getEmail())
                    .build();

            return new PrincipalDetails(member);
        }
        Member member = optionalMember.get();

        return new PrincipalDetails(member);
    }
}
