package com.example.Allermi.domain.item.dto;

import com.example.Allermi.domain.item.Entity.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class itemResponseDto {

    private String number; // 제목

    private String name; // 내용

    private String ingredient;

    private String allergy;

    private String type;

    private String company;

    private String imageURL;

    private String metaURL;

    private String nutrient;


    public itemResponseDto(Item item) {
        this.number = item.getNumber();
        this.name = item.getName();
        this.ingredient = item.getIngredient();
        this.allergy = item.getAllergy();
        this.type = item.getType();
        this.company = item.getCompany();
        this.imageURL = item.getImageURL();
        this.metaURL = item.getMetaURL();
        this.nutrient = item.getNutrient();
    }
}
