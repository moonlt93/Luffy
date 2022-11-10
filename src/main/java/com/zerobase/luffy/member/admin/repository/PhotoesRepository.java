package com.zerobase.luffy.member.admin.repository;

import com.zerobase.luffy.member.admin.entity.Photoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoesRepository extends JpaRepository<Photoes,Long> {



}
