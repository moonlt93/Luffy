package com.zerobase.luffy.member.common.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
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

}
