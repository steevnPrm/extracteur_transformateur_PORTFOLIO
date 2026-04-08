package com.steevnPrm.github.fluxExtractor.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import com.steevnPrm.github.fluxExtractor.entity.ItemEntity;

@Service
public class ItemOrchestrator {

    private static final Logger logger = LoggerFactory.getLogger(ItemOrchestrator.class);

    private final ItemGenerator generator;
    private final ItemTransformator transformator;
    private final ItemLoader loader;

    public ItemOrchestrator(ItemTransformator transformator, ItemLoader loader, ItemGenerator generator) {
        this.transformator = transformator;
        this.loader = loader;
        this.generator = generator;
    }

    public long execute() {
        StopWatch sw = new StopWatch("Flux ETL");

        sw.start("Génération");
        List<ItemEntity> rawItems = generator.execute();
        sw.stop();

        sw.start("Transformation");
        List<ItemEntity> cleanData = transformator.execute(rawItems);
        sw.stop();

        sw.start("Chargement");
        loader.execute(cleanData);
        sw.stop();

        logger.info("\n{}", sw.prettyPrint());
        
        return sw.getTotalTimeMillis();
    }
}