package com.steevnPrm.github.fluxExtractor.service;

import java.util.ArrayList;
import java.util.List; 
import com.steevnPrm.github.fluxExtractor.entity.ItemEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemGenerator { 

    private final ItemService iService;

    public ItemGenerator(ItemService iService) {
        this.iService = iService;
    }
    
    public void runThousandItems() { 
        List<ItemEntity> myList = new ArrayList<>();
        
        for(int i = 0; i < 1000; i++) {
           
            ItemEntity nouvelItem = iService.createItem(i, "Item numéro " + i);
            myList.add(nouvelItem);
        }
    
        iService.processFilterAndSave(myList);
    }
}