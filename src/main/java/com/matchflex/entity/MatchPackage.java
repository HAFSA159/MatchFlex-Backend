package com.matchflex.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "match_packages")
public class MatchPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long packageId;

    private String packageType;
    private LocalDateTime purchaseDate;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "band_id")
    private SmartBand smartBand;

    @OneToMany(mappedBy = "matchPackage")
    private List<Match> matches;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private AbonnementPlan abonnementPlan;
}

