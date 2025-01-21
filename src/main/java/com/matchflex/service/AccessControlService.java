package com.matchflex.service;

import com.matchflex.dto.AccessControlDTO;
import com.matchflex.entity.Enum.EntryStatus;
import java.time.LocalDateTime;
import java.util.List;

public interface AccessControlService {
    AccessControlDTO createAccessControl(AccessControlDTO accessControlDTO);
    AccessControlDTO getAccessControlById(Long id);
    List<AccessControlDTO> getAllAccessControls();
    List<AccessControlDTO> getAccessControlsByEntryStatus(EntryStatus status);
    List<AccessControlDTO> getAccessControlsByTimeRange(LocalDateTime start, LocalDateTime end);
    AccessControlDTO updateAccessControl(Long id, AccessControlDTO accessControlDTO);
    void deleteAccessControl(Long id);
}

