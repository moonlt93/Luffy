package com.zerobase.luffy.member.common.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String phone;
    private String email;
    private String registration;


    private LocalDateTime regDt;
    private LocalDateTime EndDt;

    private String ip;
    private String ROLE;



}
