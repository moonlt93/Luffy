package com.zerobase.luffy.member.admin.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zerobase.luffy.member.admin.entity.Photoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoesRepository extends JpaRepository<Photoes,Long> {


    List<Photoes> findByproductDetail_Id(Long id);


}
