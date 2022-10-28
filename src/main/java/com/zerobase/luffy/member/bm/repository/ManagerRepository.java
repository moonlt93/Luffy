package com.zerobase.luffy.member.bm.repository;

import com.zerobase.luffy.member.bm.entity.BrandManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface ManagerRepository extends  JpaRepository<BrandManager,Long> {


    List<BrandManager> findByManagerName(String managerName);
}
