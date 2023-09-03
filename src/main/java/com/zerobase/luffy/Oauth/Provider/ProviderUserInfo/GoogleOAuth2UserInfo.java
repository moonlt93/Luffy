package com.zerobase.luffy.Oauth.Provider.ProviderUserInfo;

import com.zerobase.luffy.Oauth.Provider.ProviderOAuth2UserInfo;

import java.util.Map;

public class GoogleOAuth2UserInfo extends ProviderOAuth2UserInfo {

    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return super.getAttributes();
    }

    @Override
    public String getId() {
        return (String)attributes.get("sub");
    }

    @Override
    public String getName() {
        return (String)attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String)attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String)attributes.get("picture");
    }
}
