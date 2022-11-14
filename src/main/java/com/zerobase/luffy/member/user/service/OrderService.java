package com.zerobase.luffy.member.user.service;

import com.zerobase.luffy.member.user.dto.OrderDto;
import com.zerobase.luffy.member.user.entity.OrderItem;

import java.util.List;

public interface OrderService {
    Object createOrder(OrderDto dto);

    List<OrderItem> findByUserName(String name);
}
