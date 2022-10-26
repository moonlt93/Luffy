package com.zerobase.luffy.member.admin.Dto;

import com.zerobase.luffy.member.type.MemberCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MemberInput {

    Long id;

    String username;
    String name;
    String password;
    String phone;
    String email;
    String registration;


    LocalDateTime regDt;
    LocalDateTime upDt;
    LocalDateTime EndDt;


    String ip;
    String ROLE;

    MemberCode memberStatus;
    String IdList;

}
