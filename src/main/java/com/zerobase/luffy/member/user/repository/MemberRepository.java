package com.zerobase.luffy.member.user.repository;

import com.zerobase.luffy.main.entity.Wish;
import com.zerobase.luffy.member.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
   public Optional<Member> findByUsername(String username);

  @Modifying
  @Query(value="UPDATE Member m set m.wishes = :wishes WHERE m.id = :id ")
  public void createSomeThing(Long id, Wish wishes);

    @Modifying
    @Transactional
    @Query(value="delete from Member m where m.id = :id")
    void deleteIds(@Param(value="id") long id);
}
