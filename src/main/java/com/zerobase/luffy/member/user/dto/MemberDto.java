package com.zerobase.luffy.member.user.dto;

import com.zerobase.luffy.member.user.entity.Member;
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
    private Long reserve;

    public static MemberDto EntityBuild(Member member){

        return MemberDto.builder()
                .id(member.getId())
                .userName(member.getUsername())
                .ip(member.getIp())
                .ROLE(member.getROLE())
                .password(member.getPassword())
                .phone(member.getPhone())
                .regDt(member.getRegDt())
                .upDt(member.getUpDt())
                .email(member.getEmail())
                .memberStatus(String.valueOf(member.getMemberStatus()))
                .registration(member.getRegistration())
                .name(member.getName())
                .reserve(member.getReserve())
                .build();


    }




}
