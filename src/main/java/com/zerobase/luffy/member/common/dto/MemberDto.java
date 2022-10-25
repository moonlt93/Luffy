package com.zerobase.luffy.member.common.dto;

import com.zerobase.luffy.member.common.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class MemberDto {

    private Long id;

    private String userName;
    private String password;
    private String phone;
    private String email;
    private String registration;
    private String name;


    private LocalDateTime regDt;
    private LocalDateTime endDt;
    private LocalDateTime upDt;

    private String ip;
    private String ROLE;

    private String memberStatus;

    public static MemberDto EntityBuild(Member member){

        return MemberDto.builder()
                .id(member.getId())
                .userName(member.getUsername())
                .ip(member.getIp())
                .ROLE(member.getROLE())
                .password(member.getPassword())
                .phone(member.getPhone())
                .regDt(member.getRegDt())
                .registration(member.getRegistration())
                .name(member.getName())
                .build();


    }




}
