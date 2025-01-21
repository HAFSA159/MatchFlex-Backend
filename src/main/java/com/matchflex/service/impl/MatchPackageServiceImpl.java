package com.matchflex.service.impl;

import com.matchflex.dto.MatchPackageDTO;
import com.matchflex.entity.MatchPackage;
import com.matchflex.entity.SmartBand;
import com.matchflex.repository.MatchPackageRepository;
import com.matchflex.repository.SmartBandRepository;
import com.matchflex.service.MatchPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MatchPackageServiceImpl implements MatchPackageService {

    private final MatchPackageRepository matchPackageRepository;
    private final SmartBandRepository smartBandRepository;

    @Autowired
    public MatchPackageServiceImpl(MatchPackageRepository matchPackageRepository, SmartBandRepository smartBandRepository) {
        this.matchPackageRepository = matchPackageRepository;
        this.smartBandRepository = smartBandRepository;
    }

    @Override
    public MatchPackageDTO createMatchPackage(MatchPackageDTO matchPackageDTO) {
        MatchPackage matchPackage = convertToEntity(matchPackageDTO);
        MatchPackage savedMatchPackage = matchPackageRepository.save(matchPackage);
        return convertToDTO(savedMatchPackage);
    }

    @Override
    public MatchPackageDTO getMatchPackageById(Long id) {
        MatchPackage matchPackage = matchPackageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("MatchPackage not found with id: " + id));
        return convertToDTO(matchPackage);
    }

    @Override
    public List<MatchPackageDTO> getAllMatchPackages() {
        return matchPackageRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MatchPackageDTO> getMatchPackagesBySmartBand(Long bandId) {
        return matchPackageRepository.findBySmartBandBandId(bandId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MatchPackageDTO> getMatchPackagesByType(String packageType) {
        return matchPackageRepository.findByPackageType(packageType).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MatchPackageDTO updateMatchPackage(Long id, MatchPackageDTO matchPackageDTO) {
        MatchPackage existingMatchPackage = matchPackageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("MatchPackage not found with id: " + id));

        existingMatchPackage.setPackageType(matchPackageDTO.getPackageType());
        existingMatchPackage.setPurchaseDate(matchPackageDTO.getPurchaseDate());
        existingMatchPackage.setPrice(matchPackageDTO.getPrice());

        MatchPackage updatedMatchPackage = matchPackageRepository.save(existingMatchPackage);
        return convertToDTO(updatedMatchPackage);
    }

    @Override
    public void deleteMatchPackage(Long id) {
        if (!matchPackageRepository.existsById(id)) {
            throw new IllegalArgumentException("MatchPackage not found with id: " + id);
        }
        matchPackageRepository.deleteById(id);
    }

    @Override
    public MatchPackageDTO assignToSmartBand(Long packageId, Long bandId) {
        MatchPackage matchPackage = matchPackageRepository.findById(packageId)
                .orElseThrow(() -> new IllegalArgumentException("MatchPackage not found with id: " + packageId));
        SmartBand smartBand = smartBandRepository.findById(bandId)
                .orElseThrow(() -> new IllegalArgumentException("SmartBand not found with id: " + bandId));

        matchPackage.setSmartBand(smartBand);
        MatchPackage updatedMatchPackage = matchPackageRepository.save(matchPackage);

        return convertToDTO(updatedMatchPackage);
    }

    private MatchPackageDTO convertToDTO(MatchPackage matchPackage) {
        MatchPackageDTO dto = new MatchPackageDTO();
        dto.setPackageId(matchPackage.getPackageId());
        dto.setPackageType(matchPackage.getPackageType());
        dto.setPurchaseDate(matchPackage.getPurchaseDate());
        dto.setPrice(matchPackage.getPrice());
        if (matchPackage.getSmartBand() != null) {
            dto.setBandId(matchPackage.getSmartBand().getBandId());
        }
        if (matchPackage.getMatches() != null) {
            dto.setMatchIds(matchPackage.getMatches().stream()
                    .map(match -> match.getMatchId())
                    .collect(Collectors.toList()));
        }
        if (matchPackage.getAbonnementPlan() != null) {
            dto.setPlanId(matchPackage.getAbonnementPlan().getPlanId());
        }
        return dto;
    }

    private MatchPackage convertToEntity(MatchPackageDTO dto) {
        MatchPackage matchPackage = new MatchPackage();
        matchPackage.setPackageType(dto.getPackageType());
        matchPackage.setPurchaseDate(dto.getPurchaseDate());
        matchPackage.setPrice(dto.getPrice());
        return matchPackage;
    }
}

