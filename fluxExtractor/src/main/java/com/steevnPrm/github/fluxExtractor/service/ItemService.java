package com.steevnPrm.github.fluxExtractor.service;

import java.util.UUID;

import java.util.List;

import org.springframework.stereotype.Service;

import com.steevnPrm.github.fluxExtractor.entity.ItemEntity;
import com.steevnPrm.github.fluxExtractor.entity.ItemValidationErrorEntity;
import com.steevnPrm.github.fluxExtractor.repository.ItemRepository;
import com.steevnPrm.github.fluxExtractor.repository.ItemValidationErrorRepository;


@Service
public class ItemService {
    private final ItemRepository repository;
    private final ItemValidationErrorRepository emRepository;

    public ItemService(ItemRepository repository, ItemValidationErrorRepository emRepository){
       this.repository = repository;
       this.emRepository = emRepository;
    }

    public ItemEntity createItem(Integer value, String description) {
    ItemEntity item = new ItemEntity();
    item.setId(UUID.randomUUID().toString()); 
    item.setValue(value);
    item.setDescription(description);
    return item;
    }

    public ItemValidationErrorEntity saveErrorlog(ItemEntity item){
        ItemValidationErrorEntity badItem = new ItemValidationErrorEntity();
        badItem.setErrorMessage("Log mal formatée");
        badItem.setData(item);
        return emRepository.save(badItem);

    }

    public void processFilterAndSave(List<ItemEntity> items){
        items.stream().forEach(item -> {
            if(item != null 
                && item.getValue() != null
                && item.getValue() > 0 
                && item.getDescription() != null){
                repository.save(item);
            } else {
                saveErrorlog(item);
            }
        });
    }

    public List<ItemEntity> getAllItems() {
    return repository.findAll(); 
    }

}
