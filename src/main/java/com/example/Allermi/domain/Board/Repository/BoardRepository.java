package com.example.Allermi.domain.Board.Repository;

import com.example.Allermi.domain.Board.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}