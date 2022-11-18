package com.zerobase.luffy.member.user.service;

import com.zerobase.luffy.member.user.dto.OrderDto;
import com.zerobase.luffy.member.user.dto.OrderListDto;
import com.zerobase.luffy.member.user.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    Object createOrder(OrderDto dto);

    List<OrderItem> findByUserName(String name);

    Page<OrderItem> getOrderList(Pageable pageable,String name);

    OrderDto findByOrderCode(String code);

    OrderDto findByOrderId(String code);

    void deleteOrders(OrderListDto dto);
}
