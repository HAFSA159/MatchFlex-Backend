package com.matchflex.service.impl;

import com.matchflex.dto.AbonnementPlanDTO;
import com.matchflex.entity.AbonnementPlan;
import com.matchflex.repository.AbonnementPlanRepository;
import com.matchflex.service.AbonnementPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AbonnementPlanServiceImpl implements AbonnementPlanService {

    private final AbonnementPlanRepository abonnementPlanRepository;

    @Autowired
    public AbonnementPlanServiceImpl(AbonnementPlanRepository abonnementPlanRepository) {
        this.abonnementPlanRepository = abonnementPlanRepository;
    }

    @Override
    public AbonnementPlanDTO createAbonnementPlan(AbonnementPlanDTO abonnementPlanDTO) {
        AbonnementPlan abonnementPlan = convertToEntity(abonnementPlanDTO);
        AbonnementPlan savedAbonnementPlan = abonnementPlanRepository.save(abonnementPlan);
        return convertToDTO(savedAbonnementPlan);
    }

    @Override
    public AbonnementPlanDTO getAbonnementPlanById(Long id) {
        AbonnementPlan abonnementPlan = abonnementPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("AbonnementPlan not found with id: " + id));
        return convertToDTO(abonnementPlan);
    }

    @Override
    public List<AbonnementPlanDTO> getAllAbonnementPlans() {
        return abonnementPlanRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AbonnementPlanDTO> getAbonnementPlansByMatchLimit(Integer matchLimit) {
        return abonnementPlanRepository.findByMatchLimit(matchLimit).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AbonnementPlanDTO> getAbonnementPlansByMaxPrice(Double maxPrice) {
        return abonnementPlanRepository.findByBasePriceLessThanEqual(maxPrice).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AbonnementPlanDTO updateAbonnementPlan(Long id, AbonnementPlanDTO abonnementPlanDTO) {
        AbonnementPlan existingAbonnementPlan = abonnementPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("AbonnementPlan not found with id: " + id));

        existingAbonnementPlan.setPlanname(abonnementPlanDTO.getPlanname());
        existingAbonnementPlan.setMatchLimit(abonnementPlanDTO.getMatchLimit());
        existingAbonnementPlan.setBasePrice(abonnementPlanDTO.getBasePrice());
        existingAbonnementPlan.setDescription(abonnementPlanDTO.getDescription());

        AbonnementPlan updatedAbonnementPlan = abonnementPlanRepository.save(existingAbonnementPlan);
        return convertToDTO(updatedAbonnementPlan);
    }

    @Override
    public void deleteAbonnementPlan(Long id) {
        if (!abonnementPlanRepository.existsById(id)) {
            throw new IllegalArgumentException("AbonnementPlan not found with id: " + id);
        }
        abonnementPlanRepository.deleteById(id);
    }

    private AbonnementPlanDTO convertToDTO(AbonnementPlan abonnementPlan) {
        AbonnementPlanDTO dto = new AbonnementPlanDTO();
        dto.setPlanId(abonnementPlan.getPlanId());
        dto.setPlanname(abonnementPlan.getPlanname());
        dto.setMatchLimit(abonnementPlan.getMatchLimit());
        dto.setBasePrice(abonnementPlan.getBasePrice());
        dto.setDescription(abonnementPlan.getDescription());
        if (abonnementPlan.getAvailablePackages() != null) {
            dto.setAvailablePackageIds(abonnementPlan.getAvailablePackages().stream()
                    .map(matchPackage -> matchPackage.getPackageId())
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    private AbonnementPlan convertToEntity(AbonnementPlanDTO dto) {
        AbonnementPlan abonnementPlan = new AbonnementPlan();
        abonnementPlan.setPlanname(dto.getPlanname());
        abonnementPlan.setMatchLimit(dto.getMatchLimit());
        abonnementPlan.setBasePrice(dto.getBasePrice());
        abonnementPlan.setDescription(dto.getDescription());
        return abonnementPlan;
    }
}

