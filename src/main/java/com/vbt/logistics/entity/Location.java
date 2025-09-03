package com.vbt.logistics.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "location", schema = "logistics")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    private String name;

    @Column(name = "address_line", length = 250)
    private String addressLine;

    @Column(length = 120)
    private String city;

    @Column(length = 60)
    private String country;

    @Column(name = "postal_code", length = 20)
    private String postalCode;

    private Double latitude;

    private Double longitude;
}
