package com.steevnPrm.github.fluxExtractor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.steevnPrm.github.fluxExtractor.entity.ItemEntity;
import com.steevnPrm.github.fluxExtractor.repository.ItemRepository;

@Service
public class ItemLoader {

    private final ItemRepository repository;

    public ItemLoader(ItemRepository repository){
        this.repository = repository;
    }
    public void execute(List<ItemEntity> data){
        repository.saveAll(data);
    }
}
