package com.zerobase.luffy.main.entity;

import com.zerobase.luffy.member.user.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CouponId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Member member;


    private String title;
    private int rate;
}
