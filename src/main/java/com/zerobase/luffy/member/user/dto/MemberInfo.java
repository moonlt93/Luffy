package com.zerobase.luffy.member.user.dto;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberInfo {

    private Long id;

    private String userName;
    private String password;
    private String phone;
    private String email;
    private String registration;
    private String name;



    private LocalDateTime regDt;
    private LocalDateTime upDt;

    private String ip;
    private String ROLE;

    private String memberStatus;


}
