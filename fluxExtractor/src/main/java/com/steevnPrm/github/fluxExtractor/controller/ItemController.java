package com.steevnPrm.github.fluxExtractor.controller;

import java.util.List;
import com.steevnPrm.github.fluxExtractor.entity.ItemEntity;
import com.steevnPrm.github.fluxExtractor.service.ItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<ItemEntity> getAll() {
        return itemService.getAllItems();
    }

    @PostMapping("/batch-test")
        public String launchBatchTest() {
            List<ItemEntity> testList = new java.util.ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                if (i % 10 == 0) {
                    testList.add(itemService.createItem(-100, "VALEUR NÉGATIVE TEST")); // Forcer l'algo à créer un objet lors de cas limite
                } else {
                    testList.add(itemService.createItem(i, "Item valide " + i));
                }
            }
            itemService.processFilterAndSave(testList);
            return "Processus de tri terminé !";
        }
}