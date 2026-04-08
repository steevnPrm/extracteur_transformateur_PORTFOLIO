package com.steevnPrm.github.fluxExtractor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.steevnPrm.github.fluxExtractor.service.ItemOrchestrator;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemOrchestrator orchestrator;

    // Injection par constructeur : la meilleure pratique
    public ItemController(ItemOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    /**
     * Déclenche le flux ETL complet.
     * Route : POST http://localhost:8080/api/items/run
     */
    @PostMapping("/run")
        public ResponseEntity<String> runEtlProcess() {
            try {
                long duration = orchestrator.execute();
                return ResponseEntity.ok("ETL terminé avec succès en " + duration + " ms.");
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body("Erreur : " + e.getMessage());
            }
        }
}