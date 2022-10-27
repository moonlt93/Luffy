package com.zerobase.luffy.common.entity;


import com.zerobase.luffy.member.type.MemberCode;
import lombok.*;

import javax.persistence.*;
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
    private String name;
    private String password;
    private String phone;
    private String email;
    private String registration;


    private LocalDateTime regDt;
    private LocalDateTime upDt;
    private LocalDateTime EndDt;


    private String ip;
    private String ROLE;

    @Enumerated(EnumType.STRING)
    private MemberCode memberStatus;

}
