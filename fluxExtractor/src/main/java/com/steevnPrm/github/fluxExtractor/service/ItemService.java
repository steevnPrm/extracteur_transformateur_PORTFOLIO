package com.steevnPrm.github.fluxExtractor.service;

import java.util.UUID;

import java.util.List;

import org.springframework.stereotype.Service;

import com.steevnPrm.github.fluxExtractor.entity.ItemEntity;
import com.steevnPrm.github.fluxExtractor.repository.ItemRepository;


@Service
public class ItemService {
    private final ItemRepository repository;

    public ItemService(ItemRepository repository){
       this.repository = repository;
    }

    public void createIem(Integer value, String description){
        ItemEntity item = new ItemEntity();

        item.setId(UUID.randomUUID().toString());
        item.setValue(value);
        item.setDescription(description);

        item.sanitize();
        repository.save(item);
    }

    public List<ItemEntity> getAllItems() {
    return repository.findAll(); 
    }

}
