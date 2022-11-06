package com.zerobase.luffy.member.bm.repository;

import com.zerobase.luffy.member.bm.entity.BrandManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManagerRepository extends  JpaRepository<BrandManager,Long> {



    Optional<BrandManager> findByUsername(String username);

}
