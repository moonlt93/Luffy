package com.zerobase.luffy.member.admin.repository;

import com.zerobase.luffy.member.admin.entity.Photoes;
import com.zerobase.luffy.member.bm.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo,Long> {



}
