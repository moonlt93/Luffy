package com.zerobase.luffy.main.repository;

import com.zerobase.luffy.member.admin.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<ProductDetail,Long> {
}
