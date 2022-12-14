package com.zerobase.luffy.auth;

import com.zerobase.luffy.member.user.entity.Member;
import com.zerobase.luffy.member.user.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<Member> optionalMember = memberRepository.findByUsername(username);

        if(optionalMember.isEmpty()){
           throw new UsernameNotFoundException("username이 없습니다.");
        }
        Member member = optionalMember.get();

        return new PrincipalDetails(member);
    }
}
