package com.zerobase.luffy.member.user.dto;


import com.zerobase.luffy.member.type.PaymentStatus;
import com.zerobase.luffy.member.type.PaymentType;
import com.zerobase.luffy.member.user.entity.OrderItem;
import com.zerobase.luffy.member.user.entity.Payment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class PaymentDto {


    private Long paymentId;
    private Long totalPrice;

    private PaymentType paymentType;

    private PaymentStatus paymentStatus;

    private List<OrderItem> orderItem;
    private int tax;

    private String productComment;
    private Long productId;
    private String username;
    private int reserve;
    private int rate;
    private Long orderId;
    private Long reservePay;
    private boolean couponYn;


    public static PaymentDto of(Payment pay) {

            return  PaymentDto.builder()
                    .productComment(pay.getProductComment())
                    .paymentId(pay.getPaymentId())
                    .totalPrice(pay.getTotalPrice())
                    .username(pay.getUsername())
                    .build();

    }
}
