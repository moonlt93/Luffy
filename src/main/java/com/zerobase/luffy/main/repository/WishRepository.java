package com.zerobase.luffy.main.repository;

import com.zerobase.luffy.main.entity.Wish;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OneToMany;
import java.util.List;
import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish,Long> {

    List<Wish> findByMember_Id(Long id);

    @Transactional
    @Modifying
    @Query(value="delete from Wish w where w.wishId = :id")
    void deleteId(@Param(value="id") long id);
}
