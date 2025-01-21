package com.matchflex.entity;

import com.matchflex.entity.Enum.EntryStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "access_controls")
public class AccessControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accessId;

    private String planname;
    private LocalDateTime accessTime;
    private Double basePrice;

    @Enumerated(EnumType.STRING)
    private EntryStatus entryStatus;

    private String reasonForDenial;

    @ManyToMany
    @JoinTable(
            name = "access_control_packages",
            joinColumns = @JoinColumn(name = "access_id"),
            inverseJoinColumns = @JoinColumn(name = "package_id")
    )
    private List<MatchPackage> availablePackages;
}

