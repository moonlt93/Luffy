package com.zerobase.luffy.member.user.repository;

import com.zerobase.luffy.main.entity.Wish;
import com.zerobase.luffy.member.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
   public Optional<Member> findByUsername(String username);

  List<Member> findUserListByUsername(String username);
  @Modifying
  @Query(value="UPDATE Member m set m.wishes = :wishes WHERE m.id = :id ")
  public void createSomeThing(Long id, Wish wishes);


}
