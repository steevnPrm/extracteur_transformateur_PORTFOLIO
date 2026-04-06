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

    @PostMapping
    public String create(@RequestParam(required = false) Integer value, 
                         @RequestParam(required = false) String description) {
        itemService.createIem(value, description);
        return "Item créé avec succès (vérifie ta DB Postgres !)";
    }
}