package com.example.Allermi.domain.item.Repository;

import com.example.Allermi.domain.item.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface itemRepository extends JpaRepository<Item, Long> {
    List<Item> findByNameContaining(String keyword);
    Item findByNumberContaining(String keyword);
}