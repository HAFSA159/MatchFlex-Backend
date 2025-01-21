package com.matchflex.dto;

import com.matchflex.entity.Enum.EntryStatus;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class AccessControlDTO {
    private Long accessId;
    private String planname;
    private LocalDateTime accessTime;
    private Double basePrice;
    private EntryStatus entryStatus;
    private String reasonForDenial;
    private List<Long> availablePackageIds;
}

