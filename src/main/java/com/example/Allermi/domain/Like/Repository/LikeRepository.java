package com.example.Allermi.domain.Like.Repository;

import com.example.Allermi.domain.Board.Entity.Board;
import com.example.Allermi.domain.Like.Entity.Likes;
import com.example.Allermi.domain.User.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface LikeRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByMemberAndBoard(Member member, Board board);
}