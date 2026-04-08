package com.steevnPrm.github.fluxExtractor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;
import com.steevnPrm.github.fluxExtractor.entity.ItemEntity;

@Service
public class ItemGenerator {

    public List<ItemEntity> execute() {
        List<ItemEntity> items = new ArrayList<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < 1000; i++) {
            ItemEntity item = new ItemEntity();
            
            item.setValue(random.nextInt(0, 501));

            if (random.nextDouble() > 0.15) {
                item.setDescription("Produit organique type-" + random.nextInt(1, 50));
            } else {
                item.setDescription(null);
            }
            
            items.add(item);
        }

        return items;
    }
}