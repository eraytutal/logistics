package com.vbt.logistics.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "driver", schema = "logistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "full_name", length = 120, nullable = false)
    private String fullName;


    @Column(name = "license_no", length = 50)
    private String licenseNo;


    @Column(length = 20)
    private String phone;
}