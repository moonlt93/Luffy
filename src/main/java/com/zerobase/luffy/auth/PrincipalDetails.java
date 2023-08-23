package com.zerobase.luffy.auth;

import com.zerobase.luffy.member.user.entity.Member;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@NoArgsConstructor
public class PrincipalDetails implements UserDetails, OAuth2User {


    private  Member member = new Member();
    private  Map<String,Object> attributes = new HashMap<>();
    @Override
    public String getName() {
        return (String) attributes.get("sub");
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    public PrincipalDetails(Member member){
        this.member = member;
    }
    public PrincipalDetails(Map<String,Object> attributes){
        this.attributes = attributes;
    }


   public PrincipalDetails(Member member, Map<String, Object> attributes){
       this.member =member;
       this.attributes = attributes;
   }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {

                return member.getROLE();
            }
        });

       return collect;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
