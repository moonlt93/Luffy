package com.zerobase.luffy.member.bm.repository;

import com.zerobase.luffy.member.bm.entity.ManagerProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerProRepository extends JpaRepository<ManagerProduct, Long> {

    Page<ManagerProduct> findByProductNameContaining(String keyword, Pageable pageable);
}
