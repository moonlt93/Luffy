package com.zerobase.luffy.member.user.entity;


import com.zerobase.luffy.main.entity.Coupon;
import com.zerobase.luffy.member.type.MemberCode;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private LocalDateTime endDt;


    private String ip;
    private String ROLE;
    private int reserve;

    @Enumerated(EnumType.STRING)
    private MemberCode memberStatus;

    @OneToMany(mappedBy="member" ,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @OneToMany(mappedBy = "member" ,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Coupon> coupons = new ArrayList<>();


}
