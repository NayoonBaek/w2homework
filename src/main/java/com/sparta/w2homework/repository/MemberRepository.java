package com.sparta.w2homework.repository;

import com.sparta.w2homework.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findBynickname(String nickname);
    boolean existsByNickname(String nickname);
}
