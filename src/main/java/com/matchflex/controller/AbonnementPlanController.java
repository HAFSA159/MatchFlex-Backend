package com.matchflex.controller;

import com.matchflex.dto.AbonnementPlanDTO;
import com.matchflex.service.AbonnementPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/abonnementplans")
public class AbonnementPlanController {

    private final AbonnementPlanService abonnementPlanService;

    @Autowired
    public AbonnementPlanController(AbonnementPlanService abonnementPlanService) {
        this.abonnementPlanService = abonnementPlanService;
    }

    @PostMapping
    public ResponseEntity<AbonnementPlanDTO> createAbonnementPlan(@RequestBody AbonnementPlanDTO abonnementPlanDTO) {
        AbonnementPlanDTO createdAbonnementPlan = abonnementPlanService.createAbonnementPlan(abonnementPlanDTO);
        return new ResponseEntity<>(createdAbonnementPlan, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AbonnementPlanDTO> getAbonnementPlanById(@PathVariable Long id) {
        AbonnementPlanDTO abonnementPlan = abonnementPlanService.getAbonnementPlanById(id);
        return ResponseEntity.ok(abonnementPlan);
    }

    @GetMapping
    public ResponseEntity<List<AbonnementPlanDTO>> getAllAbonnementPlans() {
        List<AbonnementPlanDTO> abonnementPlans = abonnementPlanService.getAllAbonnementPlans();
        return ResponseEntity.ok(abonnementPlans);
    }

    @GetMapping("/matchlimit/{matchLimit}")
    public ResponseEntity<List<AbonnementPlanDTO>> getAbonnementPlansByMatchLimit(@PathVariable Integer matchLimit) {
        List<AbonnementPlanDTO> abonnementPlans = abonnementPlanService.getAbonnementPlansByMatchLimit(matchLimit);
        return ResponseEntity.ok(abonnementPlans);
    }

    @GetMapping("/maxprice/{maxPrice}")
    public ResponseEntity<List<AbonnementPlanDTO>> getAbonnementPlansByMaxPrice(@PathVariable Double maxPrice) {
        List<AbonnementPlanDTO> abonnementPlans = abonnementPlanService.getAbonnementPlansByMaxPrice(maxPrice);
        return ResponseEntity.ok(abonnementPlans);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AbonnementPlanDTO> updateAbonnementPlan(@PathVariable Long id, @RequestBody AbonnementPlanDTO abonnementPlanDTO) {
        AbonnementPlanDTO updatedAbonnementPlan = abonnementPlanService.updateAbonnementPlan(id, abonnementPlanDTO);
        return ResponseEntity.ok(updatedAbonnementPlan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbonnementPlan(@PathVariable Long id) {
        abonnementPlanService.deleteAbonnementPlan(id);
        return ResponseEntity.noContent().build();
    }
}

