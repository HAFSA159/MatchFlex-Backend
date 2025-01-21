package com.matchflex.repository;

import com.matchflex.entity.AbonnementPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AbonnementPlanRepository extends JpaRepository<AbonnementPlan, Long> {
    List<AbonnementPlan> findByMatchLimit(Integer matchLimit);
    List<AbonnementPlan> findByBasePriceLessThanEqual(Double price);
}

