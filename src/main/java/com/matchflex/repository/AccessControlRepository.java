package com.matchflex.repository;

import com.matchflex.entity.AccessControl;
import com.matchflex.entity.Enum.EntryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AccessControlRepository extends JpaRepository<AccessControl, Long> {
    List<AccessControl> findByEntryStatus(EntryStatus status);
    List<AccessControl> findByAccessTimeBetween(LocalDateTime start, LocalDateTime end);
}

