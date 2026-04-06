package com.steevnPrm.github.fluxExtractor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.steevnPrm.github.fluxExtractor.entity.ItemEntity;


public interface ItemRepository extends JpaRepository<ItemEntity, String>{
    
}
