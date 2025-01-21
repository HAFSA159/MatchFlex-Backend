package com.matchflex.dto;

import com.matchflex.entity.Enum.MatchStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MatchDTO {
    private Long matchId;
    private String homeTeam;
    private String awayTeam;
    private LocalDateTime matchDate;
    private String venue;
    private String stage;
    private MatchStatus status;
    private Long packageId;
}

