package com.example.Allermi.domain.Board.Service;

import com.example.Allermi.domain.Board.Entity.Board;
import com.example.Allermi.domain.Board.Repository.BoardRepository;
import com.example.Allermi.domain.User.entity.Member;
import com.example.Allermi.domain.User.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Board save(String username, Board board){
        Member user =  memberRepository.findMemberByUserid(username);
        board.setMember(user);
        return boardRepository.save(board);
    }

    @Transactional
    public Page<Board> findAll(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    public void deleteById(Long id){
        boardRepository.deleteById(id);
    }

}
