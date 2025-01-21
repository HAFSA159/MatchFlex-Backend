package com.matchflex.service.impl;

import com.matchflex.dto.AccessControlDTO;
import com.matchflex.entity.AccessControl;
import com.matchflex.entity.Enum.EntryStatus;
import com.matchflex.repository.AccessControlRepository;
import com.matchflex.service.AccessControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccessControlServiceImpl implements AccessControlService {

    private final AccessControlRepository accessControlRepository;

    @Autowired
    public AccessControlServiceImpl(AccessControlRepository accessControlRepository) {
        this.accessControlRepository = accessControlRepository;
    }

    @Override
    public AccessControlDTO createAccessControl(AccessControlDTO accessControlDTO) {
        AccessControl accessControl = convertToEntity(accessControlDTO);
        AccessControl savedAccessControl = accessControlRepository.save(accessControl);
        return convertToDTO(savedAccessControl);
    }

    @Override
    public AccessControlDTO getAccessControlById(Long id) {
        AccessControl accessControl = accessControlRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("AccessControl not found with id: " + id));
        return convertToDTO(accessControl);
    }

    @Override
    public List<AccessControlDTO> getAllAccessControls() {
        return accessControlRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccessControlDTO> getAccessControlsByEntryStatus(EntryStatus status) {
        return accessControlRepository.findByEntryStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccessControlDTO> getAccessControlsByTimeRange(LocalDateTime start, LocalDateTime end) {
        return accessControlRepository.findByAccessTimeBetween(start, end).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AccessControlDTO updateAccessControl(Long id, AccessControlDTO accessControlDTO) {
        AccessControl existingAccessControl = accessControlRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("AccessControl not found with id: " + id));

        existingAccessControl.setPlanname(accessControlDTO.getPlanname());
        existingAccessControl.setAccessTime(accessControlDTO.getAccessTime());
        existingAccessControl.setBasePrice(accessControlDTO.getBasePrice());
        existingAccessControl.setEntryStatus(accessControlDTO.getEntryStatus());
        existingAccessControl.setReasonForDenial(accessControlDTO.getReasonForDenial());

        AccessControl updatedAccessControl = accessControlRepository.save(existingAccessControl);
        return convertToDTO(updatedAccessControl);
    }

    @Override
    public void deleteAccessControl(Long id) {
        if (!accessControlRepository.existsById(id)) {
            throw new IllegalArgumentException("AccessControl not found with id: " + id);
        }
        accessControlRepository.deleteById(id);
    }

    private AccessControlDTO convertToDTO(AccessControl accessControl) {
        AccessControlDTO dto = new AccessControlDTO();
        dto.setAccessId(accessControl.getAccessId());
        dto.setPlanname(accessControl.getPlanname());
        dto.setAccessTime(accessControl.getAccessTime());
        dto.setBasePrice(accessControl.getBasePrice());
        dto.setEntryStatus(accessControl.getEntryStatus());
        dto.setReasonForDenial(accessControl.getReasonForDenial());
        if (accessControl.getAvailablePackages() != null) {
            dto.setAvailablePackageIds(accessControl.getAvailablePackages().stream()
                    .map(matchPackage -> matchPackage.getPackageId())
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    private AccessControl convertToEntity(AccessControlDTO dto) {
        AccessControl accessControl = new AccessControl();
        accessControl.setPlanname(dto.getPlanname());
        accessControl.setAccessTime(dto.getAccessTime());
        accessControl.setBasePrice(dto.getBasePrice());
        accessControl.setEntryStatus(dto.getEntryStatus());
        accessControl.setReasonForDenial(dto.getReasonForDenial());
        return accessControl;
    }
}

