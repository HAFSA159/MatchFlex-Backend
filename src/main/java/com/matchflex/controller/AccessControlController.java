package com.matchflex.controller;

import com.matchflex.dto.AccessControlDTO;
import com.matchflex.entity.Enum.EntryStatus;
import com.matchflex.service.AccessControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/accesscontrols")
public class AccessControlController {

    private final AccessControlService accessControlService;

    @Autowired
    public AccessControlController(AccessControlService accessControlService) {
        this.accessControlService = accessControlService;
    }

    @PostMapping
    public ResponseEntity<AccessControlDTO> createAccessControl(@RequestBody AccessControlDTO accessControlDTO) {
        AccessControlDTO createdAccessControl = accessControlService.createAccessControl(accessControlDTO);
        return new ResponseEntity<>(createdAccessControl, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccessControlDTO> getAccessControlById(@PathVariable Long id) {
        AccessControlDTO accessControl = accessControlService.getAccessControlById(id);
        return ResponseEntity.ok(accessControl);
    }

    @GetMapping
    public ResponseEntity<List<AccessControlDTO>> getAllAccessControls() {
        List<AccessControlDTO> accessControls = accessControlService.getAllAccessControls();
        return ResponseEntity.ok(accessControls);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<AccessControlDTO>> getAccessControlsByEntryStatus(@PathVariable EntryStatus status) {
        List<AccessControlDTO> accessControls = accessControlService.getAccessControlsByEntryStatus(status);
        return ResponseEntity.ok(accessControls);
    }

    @GetMapping("/timerange")
    public ResponseEntity<List<AccessControlDTO>> getAccessControlsByTimeRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<AccessControlDTO> accessControls = accessControlService.getAccessControlsByTimeRange(start, end);
        return ResponseEntity.ok(accessControls);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccessControlDTO> updateAccessControl(@PathVariable Long id, @RequestBody AccessControlDTO accessControlDTO) {
        AccessControlDTO updatedAccessControl = accessControlService.updateAccessControl(id, accessControlDTO);
        return ResponseEntity.ok(updatedAccessControl);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccessControl(@PathVariable Long id) {
        accessControlService.deleteAccessControl(id);
        return ResponseEntity.noContent().build();
    }
}

