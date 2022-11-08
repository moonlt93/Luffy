package com.zerobase.luffy.member.admin.repository;

import com.zerobase.luffy.member.admin.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PhotoRepository extends JpaRepository<Photo,Long> {

}
