package com.zerobase.luffy.Oauth.Provider;

import com.zerobase.luffy.Oauth.Provider.ProviderUserInfo.GoogleOAuth2UserInfo;
import com.zerobase.luffy.Oauth.Provider.ProviderUserInfo.KaKaoOAuth2UserInfo;
import com.zerobase.luffy.auth.PrincipalDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class OAuth2UserInfoFactory {

        public static ProviderOAuth2UserInfo getOAuth2UserInfo(ProviderType providerType, Map<String,Object> attributes){
            switch(providerType){
                case GOOGLE: return new GoogleOAuth2UserInfo(attributes);
                case KAKAO: return new KaKaoOAuth2UserInfo(attributes);
                default: throw new IllegalArgumentException("Invalid Provider Type");
            }
        }
}
