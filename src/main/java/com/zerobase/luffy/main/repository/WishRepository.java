package com.zerobase.luffy.main.repository;

import com.zerobase.luffy.main.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish,Long> {

    List<Wish> findByMember_Id(Long id);


}
