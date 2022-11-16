package com.zerobase.luffy.member.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderDto {


    private Long OrderId;
    private String orderCode;

    private String companyName;
    private String productName;
    private String productSize;
    private String productColor;
    private Long price;
    private Long reserve; //적립금.
    private String CategoryName;
    private int coupon;
    private int count;
    private boolean couponYn;
    private String comment;
    private int productCount;

    // 멤버정보
    private String username;

    // 회사 정보
    private Long productId;
}
