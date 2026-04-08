package com.steevnPrm.github.fluxExtractor.service;

import org.springframework.stereotype.Service;

import com.steevnPrm.github.fluxExtractor.entity.ItemEntity;

@Service
public class ItemExtractor{
    public ItemEntity execute(){
        ItemEntity item = new ItemEntity();
        return item;
    }
}
