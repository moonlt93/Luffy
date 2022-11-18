package com.zerobase.luffy.member.user.service;

import com.zerobase.luffy.main.entity.Coupon;
import com.zerobase.luffy.member.user.dto.PaymentDto;

import java.util.List;

public interface PaymentService {
    List<Coupon> getCoupons(Long id);

    PaymentDto addPayment(PaymentDto dto) throws InterruptedException;

    PaymentDto seletMyPayList(Long paymentId);
}
