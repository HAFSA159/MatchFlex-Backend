package com.matchflex.repository;

import com.matchflex.entity.MatchPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MatchPackageRepository extends JpaRepository<MatchPackage, Long> {
    List<MatchPackage> findBySmartBandBandId(Long bandId);
    List<MatchPackage> findByPackageType(String packageType);
}

