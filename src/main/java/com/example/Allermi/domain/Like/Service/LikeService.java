package com.example.Allermi.domain.Like.Service;

import com.example.Allermi.domain.Board.Entity.Board;
import com.example.Allermi.domain.Board.Repository.BoardRepository;
import com.example.Allermi.domain.Like.Entity.Likes;
import com.example.Allermi.domain.Like.Repository.LikeRepository;
import com.example.Allermi.domain.User.entity.Member;
import com.example.Allermi.domain.User.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public boolean addLike(String username, Long boardId) {
        Member user =  memberRepository.findMemberByUserid(username);
        Board recipe = boardRepository.findById(boardId).orElseThrow();

        //중복 좋아요 방지
        if(isNotAlreadyLike(username, recipe)) {
            likeRepository.save(new Likes(user, recipe));
            return true;
        }

        return false;
    }

    //사용자가 이미 좋아요 한 게시물인지 체크
    private boolean isNotAlreadyLike(String username, Board board) {
        Member user =  memberRepository.findMemberByUserid(username);
        return likeRepository.findByMemberAndBoard(user, board).isEmpty();
    }
}
