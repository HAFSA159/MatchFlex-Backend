package com.matchflex.controller;

import com.matchflex.dto.MatchPackageDTO;
import com.matchflex.service.MatchPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matchpackages")
public class MatchPackageController {

    private final MatchPackageService matchPackageService;

    @Autowired
    public MatchPackageController(MatchPackageService matchPackageService) {
        this.matchPackageService = matchPackageService;
    }

    @PostMapping
    public ResponseEntity<MatchPackageDTO> createMatchPackage(@RequestBody MatchPackageDTO matchPackageDTO) {
        MatchPackageDTO createdMatchPackage = matchPackageService.createMatchPackage(matchPackageDTO);
        return new ResponseEntity<>(createdMatchPackage, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchPackageDTO> getMatchPackageById(@PathVariable Long id) {
        MatchPackageDTO matchPackage = matchPackageService.getMatchPackageById(id);
        return ResponseEntity.ok(matchPackage);
    }

    @GetMapping
    public ResponseEntity<List<MatchPackageDTO>> getAllMatchPackages() {
        List<MatchPackageDTO> matchPackages = matchPackageService.getAllMatchPackages();
        return ResponseEntity.ok(matchPackages);
    }

    @GetMapping("/smartband/{bandId}")
    public ResponseEntity<List<MatchPackageDTO>> getMatchPackagesBySmartBand(@PathVariable Long bandId) {
        List<MatchPackageDTO> matchPackages = matchPackageService.getMatchPackagesBySmartBand(bandId);
        return ResponseEntity.ok(matchPackages);
    }

    @GetMapping("/type/{packageType}")
    public ResponseEntity<List<MatchPackageDTO>> getMatchPackagesByType(@PathVariable String packageType) {
        List<MatchPackageDTO> matchPackages = matchPackageService.getMatchPackagesByType(packageType);
        return ResponseEntity.ok(matchPackages);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchPackageDTO> updateMatchPackage(@PathVariable Long id, @RequestBody MatchPackageDTO matchPackageDTO) {
        MatchPackageDTO updatedMatchPackage = matchPackageService.updateMatchPackage(id, matchPackageDTO);
        return ResponseEntity.ok(updatedMatchPackage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatchPackage(@PathVariable Long id) {
        matchPackageService.deleteMatchPackage(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{packageId}/assign/{bandId}")
    public ResponseEntity<MatchPackageDTO> assignToSmartBand(@PathVariable Long packageId, @PathVariable Long bandId) {
        MatchPackageDTO assignedMatchPackage = matchPackageService.assignToSmartBand(packageId, bandId);
        return ResponseEntity.ok(assignedMatchPackage);
    }
}

