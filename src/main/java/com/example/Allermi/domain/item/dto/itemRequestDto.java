package com.example.Allermi.domain.item.dto;

import com.example.Allermi.domain.item.Entity.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class itemRequestDto {

    private String number; // 제목
    private String name; // 내용

    private String ingredient;

    private String allergy;

    private String type;

    private String company;

    private String imageURL;

    private String metaURL;

    private String nutrient;

    public Item toEntity() {
        return Item.builder()
                .number(number)
                .name(name)
                .ingredient(ingredient)
                .allergy(allergy)
                .type(type)
                .company(company)
                .imageURL(imageURL)
                .metaURL(metaURL)
                .nutrient(nutrient)
                .build();
    }
}
