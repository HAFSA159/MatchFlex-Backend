package com.matchflex.repository;

import com.matchflex.entity.Enum.BandStatus;
import com.matchflex.entity.SmartBand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SmartBandRepository extends JpaRepository<SmartBand, Long> {
    Optional<SmartBand> findBySerialNumber(String serialNumber);
    List<SmartBand> findByStatus(BandStatus status);
    boolean existsBySerialNumber(String serialNumber);
}

