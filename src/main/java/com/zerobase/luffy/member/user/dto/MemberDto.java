package com.zerobase.luffy.member.user.dto;

import com.zerobase.luffy.member.user.entity.Member;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class MemberDto {

    private Long id;

    @NotNull(message="username 키값이 없습니다.")
    @NotBlank(message = "유저네임을 입력하세요.")
    @Size(max = 20,message = "유저네임 길이를 초과하였습니다.")
    private String userName;


    @NotBlank(message="username 키값이 없습니다.")
    @Positive(message="양수가 아닙니다.")
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
