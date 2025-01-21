package com.matchflex.repository;

import com.matchflex.entity.Enum.MatchStatus;
import com.matchflex.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByStatus(MatchStatus status);
    List<Match> findByMatchDateBetween(LocalDateTime start, LocalDateTime end);
    List<Match> findByHomeTeamOrAwayTeam(String homeTeam, String awayTeam);
}

