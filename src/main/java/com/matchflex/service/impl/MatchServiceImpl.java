package com.matchflex.service.impl;

import com.matchflex.dto.MatchDTO;
import com.matchflex.entity.Enum.MatchStatus;
import com.matchflex.entity.Match;
import com.matchflex.repository.MatchRepository;
import com.matchflex.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public MatchDTO createMatch(MatchDTO matchDTO) {
        Match match = convertToEntity(matchDTO);
        Match savedMatch = matchRepository.save(match);
        return convertToDTO(savedMatch);
    }

    @Override
    public MatchDTO getMatchById(Long id) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Match not found with id: " + id));
        return convertToDTO(match);
    }

    @Override
    public List<MatchDTO> getAllMatches() {
        return matchRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MatchDTO> getMatchesByStatus(MatchStatus status) {
        return matchRepository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MatchDTO> getMatchesByDateRange(LocalDateTime start, LocalDateTime end) {
        return matchRepository.findByMatchDateBetween(start, end).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MatchDTO> getMatchesByTeam(String teamName) {
        return matchRepository.findByHomeTeamOrAwayTeam(teamName, teamName).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MatchDTO updateMatch(Long id, MatchDTO matchDTO) {
        Match existingMatch = matchRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Match not found with id: " + id));

        existingMatch.setHomeTeam(matchDTO.getHomeTeam());
        existingMatch.setAwayTeam(matchDTO.getAwayTeam());
        existingMatch.setMatchDate(matchDTO.getMatchDate());
        existingMatch.setVenue(matchDTO.getVenue());
        existingMatch.setStage(matchDTO.getStage());
        existingMatch.setStatus(matchDTO.getStatus());

        Match updatedMatch = matchRepository.save(existingMatch);
        return convertToDTO(updatedMatch);
    }

    @Override
    public void deleteMatch(Long id) {
        if (!matchRepository.existsById(id)) {
            throw new IllegalArgumentException("Match not found with id: " + id);
        }
        matchRepository.deleteById(id);
    }

    private MatchDTO convertToDTO(Match match) {
        MatchDTO dto = new MatchDTO();
        dto.setMatchId(match.getMatchId());
        dto.setHomeTeam(match.getHomeTeam());
        dto.setAwayTeam(match.getAwayTeam());
        dto.setMatchDate(match.getMatchDate());
        dto.setVenue(match.getVenue());
        dto.setStage(match.getStage());
        dto.setStatus(match.getStatus());
        if (match.getMatchPackage() != null) {
            dto.setPackageId(match.getMatchPackage().getPackageId());
        }
        return dto;
    }

    private Match convertToEntity(MatchDTO dto) {
        Match match = new Match();
        match.setHomeTeam(dto.getHomeTeam());
        match.setAwayTeam(dto.getAwayTeam());
        match.setMatchDate(dto.getMatchDate());
        match.setVenue(dto.getVenue());
        match.setStage(dto.getStage());
        match.setStatus(dto.getStatus());
        return match;
    }
}

