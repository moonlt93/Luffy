package com.zerobase.luffy.member.user.repository;

import com.zerobase.luffy.member.type.OrderStatus;
import com.zerobase.luffy.member.user.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Repository
public interface OrderRepository extends JpaRepository<OrderItem,Long> {
    Optional<OrderItem> findByOrderCode(String orderCode);

    List<OrderItem> findByUsername(String name);


    @Query(value=" select orders from OrderItem orders where orders.username = :username " +
            " and orders.orderStatus = com.zerobase.luffy.member.type.OrderStatus.PreCost ")
    Page<OrderItem> findByUsernameContaining(@Param(value="username") String username, Pageable pageable);



}
