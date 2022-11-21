package com.zerobase.luffy.main.repository;

import com.zerobase.luffy.main.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {

    List<Coupon> findByMember_id(Long id);
}
