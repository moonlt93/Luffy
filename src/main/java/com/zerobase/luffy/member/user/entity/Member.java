package com.zerobase.luffy.member.user.entity;


import com.zerobase.luffy.main.entity.Coupon;
import com.zerobase.luffy.main.entity.Wish;
import com.zerobase.luffy.member.type.MemberCode;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@DynamicUpdate
@DynamicInsert
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
    private String CompanyName;


    private LocalDateTime regDt;
    private LocalDateTime upDt;
    private LocalDateTime endDt;

    private String provider;
    private String providerId;


    private String ip;
    private String ROLE;
    private Long reserve;

    @Enumerated(EnumType.STRING)
    private MemberCode memberStatus;

    @Builder.Default
    @OneToMany(mappedBy="member" ,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<OrderItem>();
    @Builder.Default
    @OneToMany(mappedBy = "member" ,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Coupon> coupons = new ArrayList<Coupon>();
    @Builder.Default
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Wish> wishes = new ArrayList<Wish>();


    public void addWishes(final Wish wish){
        wishes.clear();
        wishes.add(wish);
        wish.setMember(this);


    }


}
