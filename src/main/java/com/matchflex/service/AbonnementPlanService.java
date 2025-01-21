package com.matchflex.service;

import com.matchflex.dto.AbonnementPlanDTO;
import java.util.List;

public interface AbonnementPlanService {
    AbonnementPlanDTO createAbonnementPlan(AbonnementPlanDTO abonnementPlanDTO);
    AbonnementPlanDTO getAbonnementPlanById(Long id);
    List<AbonnementPlanDTO> getAllAbonnementPlans();
    List<AbonnementPlanDTO> getAbonnementPlansByMatchLimit(Integer matchLimit);
    List<AbonnementPlanDTO> getAbonnementPlansByMaxPrice(Double maxPrice);
    AbonnementPlanDTO updateAbonnementPlan(Long id, AbonnementPlanDTO abonnementPlanDTO);
    void deleteAbonnementPlan(Long id);
}

