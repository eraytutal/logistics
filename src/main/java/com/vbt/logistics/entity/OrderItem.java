package com.vbt.logistics.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_item", schema = "logistics")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;


    @Column(columnDefinition = "text")
    private String description;


    @Column(name = "weight_kg", precision = 12, scale = 3)
    private BigDecimal weightKg;


    @Column(name = "volume_m3", precision = 12, scale = 3)
    private BigDecimal volumeM3;


    @Column(name = "hazard_flag", nullable = false)
    private boolean hazardFlag;


    @Column(name = "temp_control", nullable = false)
    private boolean tempControl;
}
