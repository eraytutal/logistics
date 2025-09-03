package com.vbt.logistics.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "carrier", schema = "logistics")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Carrier {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 150, nullable = false)
    private String name;


    @Column(length = 150)
    private String contact;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;
}
