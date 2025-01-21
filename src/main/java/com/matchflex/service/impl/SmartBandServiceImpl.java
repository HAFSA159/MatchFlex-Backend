package com.matchflex.service.impl;

import com.matchflex.dto.SmartBandDTO;
import com.matchflex.entity.Enum.BandStatus;
import com.matchflex.entity.SmartBand;
import com.matchflex.entity.User;
import com.matchflex.repository.SmartBandRepository;
import com.matchflex.repository.UserRepository;
import com.matchflex.service.SmartBandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SmartBandServiceImpl implements SmartBandService {

    private final SmartBandRepository smartBandRepository;
    private final UserRepository userRepository;

    @Autowired
    public SmartBandServiceImpl(SmartBandRepository smartBandRepository, UserRepository userRepository) {
        this.smartBandRepository = smartBandRepository;
        this.userRepository = userRepository;
    }

    @Override
    public SmartBandDTO createSmartBand(SmartBandDTO smartBandDTO) {
        if (isSerialNumberTaken(smartBandDTO.getSerialNumber())) {
            throw new IllegalArgumentException("Serial number is already in use");
        }
        SmartBand smartBand = convertToEntity(smartBandDTO);
        SmartBand savedSmartBand = smartBandRepository.save(smartBand);
        return convertToDTO(savedSmartBand);
    }

    @Override
    public SmartBandDTO getSmartBandById(Long id) {
        SmartBand smartBand = smartBandRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SmartBand not found with id: " + id));
        return convertToDTO(smartBand);
    }

    @Override
    public SmartBandDTO getSmartBandBySerialNumber(String serialNumber) {
        SmartBand smartBand = smartBandRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> new IllegalArgumentException("SmartBand not found with serial number: " + serialNumber));
        return convertToDTO(smartBand);
    }

    @Override
    public List<SmartBandDTO> getAllSmartBands() {
        return smartBandRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SmartBandDTO> getSmartBandsByStatus(BandStatus status) {
        return smartBandRepository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SmartBandDTO updateSmartBand(Long id, SmartBandDTO smartBandDTO) {
        SmartBand existingSmartBand = smartBandRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SmartBand not found with id: " + id));

        if (!existingSmartBand.getSerialNumber().equals(smartBandDTO.getSerialNumber()) &&
                isSerialNumberTaken(smartBandDTO.getSerialNumber())) {
            throw new IllegalArgumentException("Serial number is already in use");
        }

        existingSmartBand.setSerialNumber(smartBandDTO.getSerialNumber());
        existingSmartBand.setActivationTime(smartBandDTO.getActivationTime());
        existingSmartBand.setStatus(smartBandDTO.getStatus());

        SmartBand updatedSmartBand = smartBandRepository.save(existingSmartBand);
        return convertToDTO(updatedSmartBand);
    }

    @Override
    public void deleteSmartBand(Long id) {
        if (!smartBandRepository.existsById(id)) {
            throw new IllegalArgumentException("SmartBand not found with id: " + id);
        }
        smartBandRepository.deleteById(id);
    }

    @Override
    public boolean isSerialNumberTaken(String serialNumber) {
        return smartBandRepository.existsBySerialNumber(serialNumber);
    }

    @Override
    public SmartBandDTO assignToUser(Long bandId, Long userId) {
        SmartBand smartBand = smartBandRepository.findById(bandId)
                .orElseThrow(() -> new IllegalArgumentException("SmartBand not found with id: " + bandId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        smartBand.setOwner(user);
        user.setSmartBand(smartBand);

        SmartBand updatedSmartBand = smartBandRepository.save(smartBand);
        userRepository.save(user);

        return convertToDTO(updatedSmartBand);
    }

    private SmartBandDTO convertToDTO(SmartBand smartBand) {
        SmartBandDTO dto = new SmartBandDTO();
        dto.setBandId(smartBand.getBandId());
        dto.setSerialNumber(smartBand.getSerialNumber());
        dto.setActivationTime(smartBand.getActivationTime());
        dto.setStatus(smartBand.getStatus());
        if (smartBand.getOwner() != null) {
            dto.setUserId(smartBand.getOwner().getUserId());
            dto.setUserName(smartBand.getOwner().getFirstName());
        }
        return dto;
    }

    private SmartBand convertToEntity(SmartBandDTO dto) {
        SmartBand smartBand = new SmartBand();
        smartBand.setSerialNumber(dto.getSerialNumber());
        smartBand.setActivationTime(dto.getActivationTime());
        smartBand.setStatus(dto.getStatus());
        return smartBand;
    }
}

