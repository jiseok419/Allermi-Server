package com.example.Allermi.domain.item.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Item {
    @Id
    private String number; // 제목

    private String name; // 내용

    private String ingredient;

    private String allergy;

    private String type;

    private String company;

    private String imageURL;

    private String metaURL;

    private String nutrient;

    @Builder
    public Item(String number, String name, String ingredient, String allergy, String type, String company, String imageURL, String metaURL, String nutrient) {
        this.number = number;
        this.name = name;
        this.ingredient = ingredient;
        this.allergy = allergy;
        this.type = type;
        this.company = company;
        this.imageURL = imageURL;
        this.metaURL = metaURL;
        this.nutrient = nutrient;
    }


    public void update(String number, String name, String ingredient, String allergy, String type, String company, String imageURL, String metaURL, String nutrient) {
        this.number = number;
        this.name = name;
        this.ingredient = ingredient;
        this.allergy = allergy;
        this.type = type;
        this.company = company;
        this.imageURL = imageURL;
        this.metaURL = metaURL;
        this.nutrient = nutrient;
    }
}