package com.example.Allermi.domain.Board.controller;

import com.example.Allermi.domain.Board.Entity.Board;
import com.example.Allermi.domain.Board.Service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<?> getBoard(@PageableDefault Pageable pageable, PagedResourcesAssembler<Board> assembler){
        Page<Board> boards = boardService.findAll(pageable);
        PagedModel<EntityModel<Board>> model = assembler.toModel(boards);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> postBoard(@RequestBody Board board, Authentication authentication){
        String username = authentication.getName();
        Board savedBoard = boardService.save(username, board);
        return new ResponseEntity<>(savedBoard, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable(value = "id") Long id){
        boardService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
