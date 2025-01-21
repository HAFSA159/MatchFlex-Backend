package com.matchflex.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class MatchPackageDTO {
    private Long packageId;
    private String packageType;
    private LocalDateTime purchaseDate;
    private Double price;
    private Long bandId;
    private List<Long> matchIds;
    private Long planId;
}

