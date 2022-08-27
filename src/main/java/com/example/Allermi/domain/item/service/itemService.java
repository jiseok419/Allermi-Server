package com.example.Allermi.domain.item.service;

import com.example.Allermi.domain.item.Entity.Item;
import com.example.Allermi.domain.item.Repository.itemRepository;
import com.example.Allermi.domain.item.dto.itemRequestDto;
import com.example.Allermi.domain.item.dto.itemResponseDto;
import com.example.Allermi.global.exception.CustomException;
import com.example.Allermi.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class itemService {

    private final itemRepository itemRepository;

    /**
     * 게시글 리스트 조회
     */
    public List<itemResponseDto> findAll() {

        Sort sort = Sort.by(Direction.DESC, "id", "createdDate");
        List<Item> list = itemRepository.findAll(sort);
        return list.stream().map(itemResponseDto::new).collect(Collectors.toList());
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public Long update(final Long id, final itemRequestDto params) {
        Item entity = itemRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.update(params.getNumber() ,params.getName(), params.getIngredient(), params.getAllergy(), params.getType(), params.getCompany(), params.getImageURL(), params.getMetaURL(), params.getNutrient());
        return id;
    }


    // 검색
    @Transactional
    public List<Item> search(String keyword) {
        List<Item> itemList = itemRepository.findByNameContaining(keyword);
        return itemList;
    }

    @Transactional
    public Item searchNumber(String keyword) {
        Item item = itemRepository.findByNumberContaining(keyword);
        return item;
    }
}
