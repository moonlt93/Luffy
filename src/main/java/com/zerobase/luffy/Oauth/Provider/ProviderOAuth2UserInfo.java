package com.zerobase.luffy.Oauth.Provider;

import java.util.Map;

public abstract class ProviderOAuth2UserInfo {

    protected Map<String,Object> attributes;

    public ProviderOAuth2UserInfo(Map<String,Object> attributes){
        this.attributes = attributes;
    }

    public Map<String,Object>getAttributes(){
        return attributes;
    }

    public abstract String getId();
    public abstract String getName();
    public abstract String getEmail();

    public abstract  String getImageUrl();




}
