package com.zerobase.luffy.Oauth.PrincipalDetails;

import com.zerobase.luffy.Oauth.Provider.OAuth2UserInfoFactory;
import com.zerobase.luffy.Oauth.Provider.ProviderOAuth2UserInfo;
import com.zerobase.luffy.Oauth.Provider.ProviderType;
import com.zerobase.luffy.auth.PrincipalDetails;
import com.zerobase.luffy.member.user.entity.Member;
import com.zerobase.luffy.member.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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


    private final MemberRepository memberRepository;

    //후처리 메서드
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        ProviderType providerType = ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId());

        log.info("[providerType]: 해당 provider는 무엇인가 = {}", providerType);

        ProviderOAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, oAuth2User.getAttributes());


        log.info("[userInfo Id]"+ userInfo.getId());
        log.info("[userInfo Email]"+ userInfo.getEmail());
        log.info("[userInfo Name]"+ userInfo.getName());



        /* 소셜 로그인 분기 */


        log.info("[client Registration]: " + userRequest.getClientRegistration());
        log.info("[userRequest]: " + userRequest.getAccessToken());
        log.info("[client Registration clientName]: " + userRequest.getClientRegistration().getClientName());
        log.info("[client Registration Provider Details]: " + userRequest.getClientRegistration().getProviderDetails());
        log.info("[getAttributes]" + super.loadUser(userRequest));
        System.out.println("userRequest: " + userRequest);

        String provider = userRequest.getClientRegistration().getClientName();
        String providerId = oAuth2User.getName();
        String name = (String) oAuth2User.getAttributes().get("name");
        String email = (String) oAuth2User.getAttributes().get("email");

        log.info("[provider]: " + provider);
        log.info("[providerId]: " + providerId);
        log.info("[name]" + name);
        log.info("[email]" + oAuth2User.getAttributes().get("email"));
        Optional<Member> userEntity = memberRepository.findByUsername(provider + "_" + providerId);


        Member member = null;
        if (userEntity.isEmpty()) {
            final String role = "ROLE_USER";
            member = Member.builder()
                    .username(provider + "_" + providerId)
                    .name(name)
                    .email(email)
                    .ROLE(role)
                    .build();

            memberRepository.save(member);
        } else {
            member = userEntity.get();
        }

        return new PrincipalDetails(member);
    }






}
