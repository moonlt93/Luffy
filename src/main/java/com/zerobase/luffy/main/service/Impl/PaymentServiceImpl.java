package com.zerobase.luffy.main.service.Impl;

import com.zerobase.luffy.main.entity.Coupon;
import com.zerobase.luffy.main.repository.CouponRepository;
import com.zerobase.luffy.main.service.PaymentService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final CouponRepository couponRepository;

    @Override
    public List<Coupon> getCoupons(Long id) {

        List<Coupon> coupons= couponRepository.findByMember_id(id);

        return coupons;
    }
}
