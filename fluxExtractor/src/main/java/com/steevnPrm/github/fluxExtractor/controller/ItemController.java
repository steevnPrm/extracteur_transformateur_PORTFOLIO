package com.steevnPrm.github.fluxExtractor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.steevnPrm.github.fluxExtractor.service.ItemOrchestrator;

@RestController
@RequestMapping("/api/items")
@Tag(name = "Item Orchestrator", description = "Endpoints pour la gestion et l'exécution du flux ETL FluxExtractor")
public class ItemController {

  private final ItemOrchestrator orchestrator;

  public ItemController(ItemOrchestrator orchestrator) {
    this.orchestrator = orchestrator;
  }

  @Operation(
      summary = "Exécuter le flux ETL",
      description = "Déclenche l'orchestration complète de l'extraction, la transformation et le chargement des données."
  )
  @ApiResponse(responseCode = "200", description = "Traitement terminé avec succès")
  @ApiResponse(responseCode = "500", description = "Échec critique du processus ETL")
  @PostMapping("/run")
  public ResponseEntity<ProcessResponse> runEtlProcess() {
    try {
      long duration = orchestrator.execute();
      return ResponseEntity.ok(new ProcessResponse(
          "SUCCESS", 
          "ETL terminé avec succès", 
          duration
      ));
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(new ProcessResponse(
          "ERROR", 
          e.getMessage(), 
          0
      ));
    }
  }

  record ProcessResponse(String status, String message, long durationMs) {}
}