package com.matchflex.dto;

import com.matchflex.entity.Enum.BandStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SmartBandDTO {
    private Long bandId;
    private String serialNumber;
    private LocalDateTime activationTime;
    private BandStatus status;
    private Long userId;
    private String userName;
}

