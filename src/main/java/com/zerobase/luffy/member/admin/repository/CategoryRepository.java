package com.zerobase.luffy.member.admin.repository;

import com.zerobase.luffy.member.admin.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
   @Modifying
   @Transactional
   @Query(value=" delete from Category c where c.categoryId = :id")
   void deleteCategory(@Param("id")Long id);

   @Query(value="select c from Category c where c.categoryId = :parentId and c.categoryName = :categoryName ")
   Optional<Category> findByIdAndCategoryName(@Param("parentId") Long parentId, String categoryName);
}
