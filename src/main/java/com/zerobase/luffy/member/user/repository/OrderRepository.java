package com.zerobase.luffy.member.user.repository;

import com.zerobase.luffy.member.user.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface OrderRepository extends JpaRepository<OrderItem,Long> {
    Optional<OrderItem> findByOrderCode(String orderCode);

    List<OrderItem> findByUsername(String name);
}
