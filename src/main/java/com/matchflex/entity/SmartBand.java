package com.matchflex.entity;

import com.matchflex.entity.Enum.BandStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "smart_bands")
public class SmartBand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bandId;

    private String serialNumber;
    private LocalDateTime activationTime;

    @Enumerated(EnumType.STRING)
    private BandStatus status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(mappedBy = "smartBand")
    private List<MatchPackage> assignedPackages;
}


