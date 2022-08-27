package com.example.Allermi.domain.Board.Entity;

import com.example.Allermi.domain.Like.Entity.Likes;
import com.example.Allermi.domain.User.entity.Member;
import com.example.Allermi.global.Entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Board extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    private String image;
    @JsonIgnore
    @ManyToOne
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    Set<Likes> likes = new HashSet<>();

    @Builder
    public Board(Long id, String body, String image, Member member) {
        this.id = id;
        this.body = body;
        this.image = image;
        this.member = member;
    }
}
