package com.example.Allermi.domain.item.Controller;

import com.example.Allermi.domain.item.Entity.Item;
import com.example.Allermi.domain.item.dto.itemRequestDto;
import com.example.Allermi.domain.item.dto.itemResponseDto;
import com.example.Allermi.domain.item.service.itemService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class itemController {

    private final itemService boardService;

    /**
     * 게시글 리스트 조회
     */
    @GetMapping
    @ApiOperation(value = "상품 조회", notes = "상품 조회 API")
    public List<itemResponseDto> findAll() {
        return boardService.findAll();
    }

    /**
     * 게시글 수정
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "상품 수정", notes = "상품 수정 API")
    public Long save(@PathVariable final Long id, @RequestBody final itemRequestDto params) {
        return boardService.update(id, params);
    }

    @ApiOperation(value = "음식이름으로 검색")
    @GetMapping("/search")
    public List<Item> search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<Item> searchList = boardService.search(keyword);
        model.addAttribute("searchList", searchList);
        return searchList;
    }

    @ApiOperation(value = "바코드로 검색")
    @GetMapping("/search/number")
    public Item searchNumber(@RequestParam(value = "keyword") String keyword, Model model) {
        Item searchList = boardService.searchNumber(keyword);
        model.addAttribute("searchList", searchList);
        return searchList;
    }

}
