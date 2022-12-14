package com.zerobase.luffy.member.admin.repository;

import com.zerobase.luffy.member.admin.entity.ProductDetail;
import com.zerobase.luffy.member.type.ProductCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail,Long> {


    Page<ProductDetail> findByProductNameContaining(String searchKeyword, Pageable pageable);

    Page<ProductDetail> findByProductStatusContaining(ProductCode str, Pageable pageable);

    Page<ProductDetail> findByProductStatusContainingAndCategory_CategoryId(Pageable pageable, ProductCode str, Long categoryId);
}
