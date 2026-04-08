package com.steevnPrm.github.fluxExtractor.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.steevnPrm.github.fluxExtractor.entity.ItemEntity;

@Service
public class ItemTransformator {

    public List<ItemEntity> execute(List<ItemEntity> data) {
        return data.stream()
                .map(this::applyBusinessLogic) 
                .toList(); 
    }

    private ItemEntity applyBusinessLogic(ItemEntity item) {
        if (!StringUtils.hasText(item.getDescription())) {
            item.setDescription("Description non fournie");
        }
        if (item.getValue() == null) {
            item.setValue(0);
        }
        return item;
    }
}