package com.zerobase.luffy.Oauth.PrincipalDetails;

import com.zerobase.luffy.member.user.entity.Member;
import com.zerobase.luffy.member.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {


    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final MemberRepository memberRepository;
    //후처리 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("[client Registration]: "+  userRequest.getClientRegistration());
        log.info("[userRequest]: "+  userRequest.getAccessToken());
        log.info("[client Registration clientName]: "+  userRequest.getClientRegistration().getClientName());
        log.info("[client Registration Provider Details]: "+  userRequest.getClientRegistration().getProviderDetails());
        log.info("[getAttributes]"+ super.loadUser(userRequest));
        System.out.println("userRequest: "+ userRequest);

        String username = userRequest.getClientRegistration().getClientName();

        Optional<Member> userEntity= memberRepository.findByUsername(userRequest.getClientRegistration().getClientName());



       return super.loadUser(userRequest);
    }
}
