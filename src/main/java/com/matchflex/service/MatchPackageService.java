package com.matchflex.service;

import com.matchflex.dto.MatchPackageDTO;
import java.util.List;

public interface MatchPackageService {
    MatchPackageDTO createMatchPackage(MatchPackageDTO matchPackageDTO);
    MatchPackageDTO getMatchPackageById(Long id);
    List<MatchPackageDTO> getAllMatchPackages();
    List<MatchPackageDTO> getMatchPackagesBySmartBand(Long bandId);
    List<MatchPackageDTO> getMatchPackagesByType(String packageType);
    MatchPackageDTO updateMatchPackage(Long id, MatchPackageDTO matchPackageDTO);
    void deleteMatchPackage(Long id);
    MatchPackageDTO assignToSmartBand(Long packageId, Long bandId);
}

