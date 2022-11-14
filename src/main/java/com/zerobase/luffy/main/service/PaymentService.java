package com.zerobase.luffy.main.service;

import com.zerobase.luffy.main.entity.Coupon;

import java.util.List;

public interface PaymentService  {


    List<Coupon> getCoupons(Long id);
}
