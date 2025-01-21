package com.matchflex.service;

import com.matchflex.dto.MatchDTO;
import com.matchflex.entity.Enum.MatchStatus;
import java.time.LocalDateTime;
import java.util.List;

public interface MatchService {
    MatchDTO createMatch(MatchDTO matchDTO);
    MatchDTO getMatchById(Long id);
    List<MatchDTO> getAllMatches();
    List<MatchDTO> getMatchesByStatus(MatchStatus status);
    List<MatchDTO> getMatchesByDateRange(LocalDateTime start, LocalDateTime end);
    List<MatchDTO> getMatchesByTeam(String teamName);
    MatchDTO updateMatch(Long id, MatchDTO matchDTO);
    void deleteMatch(Long id);
}

