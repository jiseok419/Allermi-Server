package com.example.Allermi.domain.Like.Entity;

import com.example.Allermi.domain.Board.Entity.Board;
import com.example.Allermi.domain.User.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Likes(Member member, Board board) {
        this.board = board;
        this.member = member;
    }
}
