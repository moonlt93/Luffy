package com.zerobase.luffy.auth;

import com.zerobase.luffy.common.entity.Member;
import com.zerobase.luffy.common.repository.MemberRepository;
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
       Optional<Member> Optionalmember = memberRepository.findByUsername(username);

        if(!Optionalmember.isPresent()){
           throw new UsernameNotFoundException("username이 없습니다.");
        }
        Member member = Optionalmember.get();

        return new PrincipalDetails(member);
    }
}
