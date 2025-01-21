package com.matchflex.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "match_package_details")
public class MatchPackageDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "package_id")
    private Long packageId;

    @Column(name = "match_id")
    private Long matchId;

    @ManyToOne
    @JoinColumn(name = "package_id", insertable = false, updatable = false)
    private MatchPackage matchPackage;

    @ManyToOne
    @JoinColumn(name = "match_id", insertable = false, updatable = false)
    private Match match;
}

