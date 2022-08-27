package com.example.Allermi.domain.Board.Dto;

import com.example.Allermi.domain.Board.Entity.Board;
import com.example.Allermi.domain.User.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardRequestDto {
    private Long id;

    private String body;

    private String image;

    private Member member;

    public Board toEntity() {
        return Board.builder()
                .id(id)
                .body(body)
                .image(image)
                .member(member)
                .build();
    }
}
