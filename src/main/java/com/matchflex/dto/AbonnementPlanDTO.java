package com.matchflex.dto;

import lombok.Data;
import java.util.List;

@Data
public class AbonnementPlanDTO {
    private Long planId;
    private String planname;
    private Integer matchLimit;
    private Double basePrice;
    private String description;
    private List<Long> availablePackageIds;
}

