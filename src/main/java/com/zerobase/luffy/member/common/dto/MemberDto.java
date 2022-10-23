package com.zerobase.luffy.member.common.dto;

import com.zerobase.luffy.member.common.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class MemberDto {

    private long id;

    private String userName;
    private String password;
    private String phone;
    private String email;
    private String registration;


    private LocalDateTime regDt;
    private LocalDateTime EndDt;

    private String ip;
    private String ROLE;

    public static MemberDto EntityBuild(Member member){

        return MemberDto.builder()
                .userName(member.getUsername())
                .ip(member.getIp())
                .ROLE(member.getROLE())
                .id(member.getId())
                .password(member.getPassword())
                .phone(member.getPhone())
                .regDt(member.getRegDt())
                .registration(member.getRegistration())
                .build();


    }

}
