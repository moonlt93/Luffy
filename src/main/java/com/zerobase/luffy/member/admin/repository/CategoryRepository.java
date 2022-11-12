package com.zerobase.luffy.member.admin.repository;

import com.zerobase.luffy.member.admin.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
   // List<Category> findAllById(Long parentId);
}
