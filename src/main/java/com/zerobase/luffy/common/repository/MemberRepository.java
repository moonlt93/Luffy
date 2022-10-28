package com.zerobase.luffy.common.repository;

import com.zerobase.luffy.common.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
   public Optional<Member> findByUsername(String username);

  List<Member> findUserListByUsername(String username);


}