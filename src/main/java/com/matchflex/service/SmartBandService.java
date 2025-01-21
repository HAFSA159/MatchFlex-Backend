package com.matchflex.service;

import com.matchflex.dto.SmartBandDTO;
import com.matchflex.entity.Enum.BandStatus;

import java.util.List;

public interface SmartBandService {
    SmartBandDTO createSmartBand(SmartBandDTO smartBandDTO);
    SmartBandDTO getSmartBandById(Long id);
    SmartBandDTO getSmartBandBySerialNumber(String serialNumber);
    List<SmartBandDTO> getAllSmartBands();
    List<SmartBandDTO> getSmartBandsByStatus(BandStatus status);
    SmartBandDTO updateSmartBand(Long id, SmartBandDTO smartBandDTO);
    void deleteSmartBand(Long id);
    boolean isSerialNumberTaken(String serialNumber);
    SmartBandDTO assignToUser(Long bandId, Long userId);
}

