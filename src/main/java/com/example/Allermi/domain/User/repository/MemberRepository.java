package com.example.Allermi.domain.User.repository;

import com.example.Allermi.domain.User.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserid(String userid);

    Member findMemberByUserid(String userid);

    boolean existsByUserid(String Userid);
}
