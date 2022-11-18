package com.zerobase.luffy.member.user.repository;

import com.zerobase.luffy.member.user.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct,Long> {

    void deleteByOrderProId(long orderId);

    Optional<OrderProduct> findByOrderItem_OrderId(long orderId);
}
