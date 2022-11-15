package com.zerobase.luffy.member.user.entity;

import com.zerobase.luffy.common.base.BaseHeader;
import com.zerobase.luffy.member.type.PaymentStatus;
import com.zerobase.luffy.member.type.PaymentType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseHeader {

    @Id
    private Long paymentId;

    private Long totalPrice;

    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(value=EnumType.STRING)
    private PaymentStatus paymentStatus;

    @OneToMany(mappedBy = "payment" ,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItem> orderItem= new ArrayList<>();

    private int tax;

    private String productComment;
    private String username;









}
