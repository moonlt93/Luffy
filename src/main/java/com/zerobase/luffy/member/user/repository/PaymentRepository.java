package com.zerobase.luffy.member.user.repository;

import com.zerobase.luffy.member.user.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
