package com.matchflex.controller;

import com.matchflex.dto.SmartBandDTO;
import com.matchflex.entity.Enum.BandStatus;
import com.matchflex.service.SmartBandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/smartbands")
public class SmartBandController {

    private final SmartBandService smartBandService;

    @Autowired
    public SmartBandController(SmartBandService smartBandService) {
        this.smartBandService = smartBandService;
    }

    @PostMapping
    public ResponseEntity<SmartBandDTO> createSmartBand(@RequestBody SmartBandDTO smartBandDTO) {
        SmartBandDTO createdSmartBand = smartBandService.createSmartBand(smartBandDTO);
        return new ResponseEntity<>(createdSmartBand, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SmartBandDTO> getSmartBandById(@PathVariable Long id) {
        SmartBandDTO smartBand = smartBandService.getSmartBandById(id);
        return ResponseEntity.ok(smartBand);
    }

    @GetMapping
    public ResponseEntity<List<SmartBandDTO>> getAllSmartBands() {
        List<SmartBandDTO> smartBands = smartBandService.getAllSmartBands();
        return ResponseEntity.ok(smartBands);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<SmartBandDTO>> getSmartBandsByStatus(@PathVariable BandStatus status) {
        List<SmartBandDTO> smartBands = smartBandService.getSmartBandsByStatus(status);
        return ResponseEntity.ok(smartBands);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SmartBandDTO> updateSmartBand(@PathVariable Long id, @RequestBody SmartBandDTO smartBandDTO) {
        SmartBandDTO updatedSmartBand = smartBandService.updateSmartBand(id, smartBandDTO);
        return ResponseEntity.ok(updatedSmartBand);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSmartBand(@PathVariable Long id) {
        smartBandService.deleteSmartBand(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check-serial")
    public ResponseEntity<Boolean> isSerialNumberTaken(@RequestParam String serialNumber) {
        boolean isTaken = smartBandService.isSerialNumberTaken(serialNumber);
        return ResponseEntity.ok(isTaken);
    }

    @PostMapping("/{bandId}/assign/{userId}")
    public ResponseEntity<SmartBandDTO> assignToUser(@PathVariable Long bandId, @PathVariable Long userId) {
        SmartBandDTO assignedSmartBand = smartBandService.assignToUser(bandId, userId);
        return ResponseEntity.ok(assignedSmartBand);
    }
}

